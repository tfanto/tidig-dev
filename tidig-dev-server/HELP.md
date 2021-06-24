# Getting Started

### Reference Documentation


prereq
Java minst 11
Maven  installerad
Docker installerad
en IDE helst intelliJ  funkar på Eclipse också  (testat på bägge)

efter att ha placerat (packat upp zip:en)   tidig-dev

till t.ex  C:\dev-java\tidig-dev
så skall det finnas två subproject
tidig-dev-db  och tidig-dev-server

kör batfilen : buildall.bat
den sätter upp en databas och populerar den med grunddata
den sätter upp servern också
kolla i DockerDesktops UI

När batfilen  är klar så måste man generera tidstransaktioner.

i projektet tidig-dev-server
test
paketet : createtimereports
så finns klassen SkapaTestData
kör den
just nu genererar den data fär 32 dagar
i konstanten GENERATE_FOR_NUMBER_OF_DAYS  skulle jag sätta den på 1000 .
Denna generering tar en stund.

Därefter kan man curla  
(alla tre skall ge data):

curl http://localhost:8080/Api/Time?apiKey=0505f8a8-482a-4219-8e3b-108203b95064
curl http://localhost:8080/Api/Employee/TimePermission?apiKey=0505f8a8-482a-4219-8e3b-108203b95064
curl http://localhost:8080/Api/Employee/SubTree?apiKey=0505f8a8-482a-4219-8e3b-108203b95064


