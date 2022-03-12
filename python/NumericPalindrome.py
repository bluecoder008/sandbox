#!/usr/bin/env python3
from APIClient import APIClient
from BaseFile import File
from BaseUtil import (debug_msg, msg, get_module_name, parse_cmd_opts,
                      pretty_json_dumps, set_debug)
from clippership.Clipper import Clipper
import json
import os
import subprocess
import sys
import time


class NumericPalindrome(object):
    usageL1 = """ a coding exercise to decide if an integer in a Palindrome  """

    @staticmethod
    def is_palindrome(number) -> bool:
        if number < 0:
            number = -number

        # find the smallest 10 multiples which is greater than number:
        m = 1
        while number >= m:
            m = m * 10

        x = number
        while x >= 10:
            left = (x * 10) // m
            right = x % 10
            if left != right:
                return False

            # string the digits on both ends
            m = m / 100
            x = (x // 10) % m

        return True


if __name__ == "__main__":
    CMD_LINE_OPTS = [
        ["-d", "--debug", "debug", "store_true", "Verbose/Debug flag.", False],
        ["", "--test", "test", "store_true", "Unit tests.", False]
    ]
    (options, args, parser) = parse_cmd_opts(
        NumericPalindrome.usageL1, CMD_LINE_OPTS, 0)
    set_debug(options.debug, get_module_name(__file__))
    assert NumericPalindrome.is_palindrome(1)
    assert NumericPalindrome.is_palindrome(121)
    assert not NumericPalindrome.is_palindrome(12345)
    assert NumericPalindrome.is_palindrome(2442)
    assert NumericPalindrome.is_palindrome(-123454321)
    msg("Everything is OK.")
