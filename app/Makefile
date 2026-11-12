run-dist:
	build\install\app\bin\app.bat file1.json file2.json
check-coverage-build:
	./gradlew spotlessCheck jacocoTestCoverageVerification build
apply-check:
	gradlew.bat spotlessApply spotlessCheck
coverage:
	gradlew.bat jacocoTestCoverageVerification