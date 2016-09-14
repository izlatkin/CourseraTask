import commands
import os, time, sys, datetime


__author__ = 'ilya.zlatkin@oracle.com'


def time_diff(seconds):
    seconds_in_day = 60 * 60 * 24
    seconds_in_hours = 60 * 60
    if seconds / seconds_in_day > 1:
        return str(round(seconds / seconds_in_day)) + "day/s"
    elif seconds_in_day / seconds_in_hours:
        return str(round(seconds / seconds_in_hours)) + " hours"
    else:
        return 'link has just been created'

def get_build_name(link, split):
    c = 'ls -l ' + str(link) + "/latest"
    out = commands.getoutput(c)
    index = int(len(out.split(split))) - 1
    build = out.split(split)[index]
    build = build.strip()
    print str(link) + "/latest -> ", build
    return build


def main():
    seconds_in_day = 60 * 60 * 24
    now_seconds = time.time()
    print "Now: ", datetime.datetime.now()
    latest_ru = "/set/java/re/jck/9/nightly/binaries"
    latest_us = "/java/re/jck/9/nightly/binaries"

    latest_affix = '/latest'
    latest_ru_seconds = os.path.getmtime(latest_ru + latest_affix)
    latest_us_seconds = os.path.getmtime(latest_us + latest_affix)

    diff_ru = now_seconds - latest_ru_seconds
    diff_us = now_seconds - latest_us_seconds

    print latest_ru + "/latest \n", time_diff(diff_ru)
    print latest_us + "/latest \n", time_diff(diff_us)

    c = 'ls -l ' + str(latest_ru)
    print c, "\n", commands.getoutput(c)
    c = 'ls -l ' + str(latest_us)
    print c, "\n", commands.getoutput(c)

    build_ru = get_build_name(latest_ru, '/')
    build_us = get_build_name(latest_us, ' ')

    if diff_ru/seconds_in_day > 1:
        print("latest link wasn't modified more that a day ")
        sys.exit(1)

    if build_ru != build_us:
        print(latest_ru + "pointed to the different build"
                          "than " + latest_us)
        #print(out_ru)
        #print(out_us)
        sys.exit(1)


if __name__ == '__main__':
    main()