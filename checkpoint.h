#ifndef __MMX_OVS_CHECKPOINT__H__
#define __MMX_OVS_CHECKPOINT__H__

#include <stdio.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <sys/times.h>

#define CHECKPOINT_LOG(msg, ...) printf("[DBG] %s:%s (%d): "msg"\n",__FILE__,  __func__, __LINE__ , ##__VA_ARGS__)
#define FAIL_GETTIME(CLK, X) CHECKPOINT_LOG("CHECKPOINT_%s: [%s] clock_gettime() failed", #CLK, #X);

#define DEFINE_RTC_CHECKPOINT(X) static struct timespec rtc_cp_##X;
#define FIRST_RTC_CHECKPOINT(X)\
{\
    if (!clock_gettime(CLOCK_REALTIME, &rtc_cp_##X))\
    {\
        CHECKPOINT_LOG("CHECKPOINT_RTC: [%s] (first): %ld sec, %ld ns", #X, rtc_cp_##X.tv_sec, rtc_cp_##X.tv_nsec);\
    } else {\
        FAIL_GETTIME(RTC, #X);\
    }\
}
#define NEXT_RTC_CHECKPOINT(X, Y) \
{\
    if (!clock_gettime(CLOCK_REALTIME, &rtc_cp_##X))\
    {\
        CHECKPOINT_LOG("CHECKPOINT_RTC: [%s] %ld sec, %ld ns, after [%s] passed %ld sec, %ld nsec", #X, rtc_cp_##X.tv_sec, rtc_cp_##X.tv_nsec, #Y,\
                (rtc_cp_##X.tv_sec > rtc_cp_##Y.tv_sec)? rtc_cp_##X.tv_sec - rtc_cp_##Y.tv_sec : 0,\
                (rtc_cp_##X.tv_nsec > rtc_cp_##Y.tv_nsec)? rtc_cp_##X.tv_nsec - rtc_cp_##Y.tv_nsec : 0);\
    } else {\
        FAIL_GETTIME(RTC, #X);\
    }\
    memcpy(&rtc_cp_##Y, &rtc_cp_##X, sizeof(rtc_cp_##Y));\
}

#define DEFINE_PCPU_CHECKPOINT(X) static struct timespec pcpu_cp_##X;
#define FIRST_PCPU_CHECKPOINT(X)\
{\
    if (!clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &pcpu_cp_##X))\
    {\
        CHECKPOINT_LOG("CHECKPOINT_PCPU: [%s] (first): %ld sec, %ld ns", #X, pcpu_cp_##X.tv_sec, pcpu_cp_##X.tv_nsec);\
    } else {\
        FAIL_GETTIME(PCPU, #X);\
    }\
}
#define NEXT_PCPU_CHECKPOINT(X, Y) \
{\
    if (!clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &pcpu_cp_##X))\
    {\
        CHECKPOINT_LOG("CHECKPOINT_PCPU: [%s] %ld sec, %ld ns, after [%s] passed %ld sec, %ld nsec", #X, pcpu_cp_##X.tv_sec, pcpu_cp_##X.tv_nsec, #Y,\
                (pcpu_cp_##X.tv_sec > pcpu_cp_##Y.tv_sec)? pcpu_cp_##X.tv_sec - pcpu_cp_##Y.tv_sec : 0,\
                (pcpu_cp_##X.tv_nsec > pcpu_cp_##Y.tv_nsec)? pcpu_cp_##X.tv_nsec - pcpu_cp_##Y.tv_nsec : 0);\
    } else {\
        FAIL_GETTIME(PCPU, #X);\
    }\
    memcpy(&pcpu_cp_##Y, &pcpu_cp_##X, sizeof(pcpu_cp_##Y));\
}

#define DEFINE_TCPU_CHECKPOINT(X) static struct timespec tcpu_cp_##X;
#define FIRST_TCPU_CHECKPOINT(X)\
{\
    if (!clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &tcpu_cp_##X))\
    {\
        CHECKPOINT_LOG("CHECKPOINT_TCPU: [%s] (first): %ld sec, %ld ns", #X, tcpu_cp_##X.tv_sec, tcpu_cp_##X.tv_nsec);\
    } else {\
        FAIL_GETTIME(TCPU, #X);\
    }\
}
#define NEXT_TCPU_CHECKPOINT(X, Y) \
{\
    if (!clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &tcpu_cp_##X))\
    {\
        CHECKPOINT_LOG("CHECKPOINT_TCPU: [%s] %ld sec, %ld ns, after [%s] passed %ld sec, %ld nsec", #X, tcpu_cp_##X.tv_sec, tcpu_cp_##X.tv_nsec, #Y,\
                (tcpu_cp_##X.tv_sec > tcpu_cp_##Y.tv_sec)? tcpu_cp_##X.tv_sec - tcpu_cp_##Y.tv_sec : 0,\
                (tcpu_cp_##X.tv_nsec > tcpu_cp_##Y.tv_nsec)? tcpu_cp_##X.tv_nsec - tcpu_cp_##Y.tv_nsec : 0);\
    } else {\
        FAIL_GETTIME(TCPU, #X);\
    }\
    memcpy(&tcpu_cp_##Y, &tcpu_cp_##X, sizeof(tcpu_cp_##Y));\
}
#endif // __MMX_OVS_CHECKPOINT_H__
