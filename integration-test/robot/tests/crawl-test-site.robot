*** Settings ***
Documentation	Initiate test site crawl and verify
Library			Process

*** Test Cases ***
Launch test crawl
	Sleep	20s	Waiting for 20s to give Kafka time to start up...
	${result}=	Run Process	launch -S -k kafka:9092 uris.tocrawl.fc http://crawl-test-site.webarchive.org.uk	shell=yes
	Should Not Contain	${result.stderr}	Traceback
	Should Contain	${result.stderr}	INFO


