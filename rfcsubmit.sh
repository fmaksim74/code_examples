#!/bin/sh

UBUS_CMD='ubus -S call rf-control rawmessage \"$( cat $fn )\"'
DIRECT_CMD='cat \"$fn\" | /usr/libexec/rpcd/rf-control call rawmessage'

# By default no file to process
FILES=""

# By default use Ubus command
SUBMIT_CMD="$UBUS_CMD"

usage()
{
cat <<EOF
Usage: rfcsubmit [option] [ command | filename ]
       option:
                -d --direct            Do not use Ubus and submit message directly to RF-Control
                -u --ubus    [default] Use Ubus service to submit a message to RF-Control
       command: -a or --all            Submit all files in current directory
                -h or --help           Show this text

                filename               JSON file with message to submit to RF-Control
EOF
}

# Main
# Parse args
while [ -n "$1" ]; do
    [ "$1" = "-h" -o "$1" = "--help" ] && { usage; exit 0; };
    [ "$1" = "-d" -o "$1" = "--direct" ] && { SUBMIT_CMD=$DIRECT_CMD; shift; continue; };
    [ "$1" = "-u" -o "$1" = "--ubus" ] && { SUBMIT_CMD=$UBUS_CMD; shift; continue; };
    [ "$1" = "-a" -o "$1" = "--all" ] && { FILES=$( find . -type f -name "*.json" ); shift; break; };
    [ -f "$1" ] && { FILES="$1"; shift; break; };
    break;
done;

# Do work
if [ -n "$FILES" ]; then
    for fn in $FILES; do echo "Submit $fn"; eval "$SUBMIT_CMD"; done;
fi

exit 0
