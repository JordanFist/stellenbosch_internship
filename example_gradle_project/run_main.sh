#!/bin/bash

# use regex to find section in "settings.gradle" in quotes
[[ $(cat settings.gradle) =~ \'.+\'$ ]]
project_name=${BASH_REMATCH}

# remove the open and close quote symbols
project_name=${project_name#\'}
project_name=${project_name%\'}

# move to correct directory and run the main java program
cd build/classes/main/
java $project_name/Main
