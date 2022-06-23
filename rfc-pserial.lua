--[[
-- Serial IO based on file events facilities.
--]]
local nixio = require "nixio"
assert(nixio, "Failed to load nixio module")

local M = {} do

    MSG_PART_SIZE = 1024  -- bytes
    MSG_POLL_TIMEOUT = 10 -- ms

    OPEN_FLAGS =  nixio.open_flags("nonblock", "rdwr")
    POLL_FLAGS = nixio.poll_flags("in", "err", "hup")
    FDS = { {fd = 0, events = POLL_FLAGS } }
    FILE = nil

    function M:open(path)
        FILE = nixio.open(path, OPEN_FLAGS)
        if FILE == nil then
            error("Cannot open device at ".. path)
            return nil
        end
        --self.FILE:setblocking(false)
        FDS[1].fd = FILE

        return self
    end

    function getPollError(event)

        local err = nil

        if event then
            if nixio.bit.check(event, 0x0001) or 
               nixio.bit.check(event, 0x0002) or
               nixio.bit.check(event, 0x0200) then
                return err
            end

            err = { code = event }

            -- Another events breaks loop with error code and message
            if nixio.bit.check(event, 0x0008) then
                err.message = "Error occured"
            elseif nixio.bit.check(event, 0x0010) then
                err.message = "Connecton closed by peer"
            elseif nixio.bit.check(event, 0x0020) then
                err.message = "Incorrect request: fd not opened"
            else
                err.message = "Uknown event"
            end
        end

        return err
    end

    -- Infinity poll on file
    -- returns:
    --      buffer (string) - read data
    --      err (table)     - error info if there is or nil
    function M:pread()
        
        local result = false
        local err = nil
        local event = nil

        -- Infinity wait for data
        local fds_n, fds_t = nixio.poll(FDS, -1)
        if fds_n then
            event = fds_t[1].revents
            err = getPollError(event)
        end

        if fds_n and not err then
            result, err = self:read()
        end

        return result, err
    end

    function M:read()

        local result = false
        local err = nil
        local event = nil

        while true do
            
            -- Read available data
            while true do
                local data = FILE:read(MSG_PART_SIZE)
                if data then
                    result = (result or "") .. data
                else
                    break
                end
            end

            -- Wait for additional data
            local fds_n, fds_t = nixio.poll(FDS, MSG_POLL_TIMEOUT)
            if fds_n then
                event = fds_t[1].revents
                err = getPollError(event)
            end

            if not fds_n or err then
                break
            end
        end

        -- Return data and error info if there is
        return result, err
    end

    function M:write(value)
        local n = 0
        local s = 0
        repeat
            s = s + FILE:write(value:sub(n * MSG_PART_SIZE + 1, (n + 1) * MSG_PART_SIZE))
            n = n + 1
        until  s >= value:len()
        return s
    end

    function M:close()
        if FILE then FILE:close() end
    end

    function M:sleep()
        return nixio.nanosleep(0)
    end
end

return M


