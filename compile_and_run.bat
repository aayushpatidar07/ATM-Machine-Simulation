@echo off
echo ===============================================
echo    Compiling ATM Machine Project...
echo ===============================================
echo.

REM Create bin directory if it doesn't exist
if not exist "bin" mkdir bin

REM Compile all Java files
javac -d bin src\com\atm\main\*.java src\com\atm\model\*.java src\com\atm\service\*.java src\com\atm\util\*.java

if %errorlevel% == 0 (
    echo [SUCCESS] Compilation successful!
    echo.
    echo ===============================================
    echo    Running ATM Application...
    echo ===============================================
    echo.
    java -cp bin com.atm.main.ATMApplication
) else (
    echo [ERROR] Compilation failed!
    pause
)
