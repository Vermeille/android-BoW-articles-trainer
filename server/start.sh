#!/bin/sh
./bow/build/bow &
sleep 10
python reddit.py
