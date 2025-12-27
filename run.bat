@echo off
echo Cleaning old reports and screenshots...

cd /d C:\Users\Aniket\git\aniket5889\opencart

REM Clean old artifacts
rmdir /s /q reports screenshots
mkdir reports screenshots

echo Starting test execution...
mvn clean test -Dexecution_env=remote

echo Execution finished.
pause
