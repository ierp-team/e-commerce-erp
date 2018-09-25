@echo off
set curdir=%~dp0
cd /d %curdir%
echo "===========================	remove dir build	==========================="
	rd /s/q build
echo "===========================	sencha app build	==========================="
	start /wait sencha app watch
pause
