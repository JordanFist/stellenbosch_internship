# Basic Gradle Guide
*Disclaimer: There are "more correct" ways of using Gradle but in my experience they are more prone to obtuse errors.
This works better for me and might work better for you, but it may not work better for you.*

## Project set up

You need the correct directory structure and files to build a project with Gradle.
The base directory structure is as follows:

> \< project root \>
> > * build.gradle
> >
> > * settings.gradle
> >
> > src
> > > main
> > > > java
> > > > > \< package name \>
> > > > > > * \<your `.java` files go here \>

You will need to decide on a name for your java package.
This example project uses the package name "cars".
This package name will be the name of the directory that stores your `.java` files (as shown above).

The following needs to be in the `settings.gradle` file:

	rootProject.name = '< package name >'

The following is all you strictly need in the `build.gradle` file:

	apply plugin: 'java'

There are some things you need in your java files to make this work too.
Namely, you need correctly declare the package of each file.
Thus, all the files in the \< package name \> directory need the following line at the top of the file:

	package \< package name \>;

This affects import statements, be sure to import files using the package structure, for example:

	import \< package name \>.\< file name \>;

## Building the Project

Once you have all your files in place, to build the project with Gradle, you need only run the `gradle build` command from the \< project root \> directory.
To make this easy (and to allow consistent building - should you change the command you want to use to build your project), I have included the `build_gradle.sh` script.
To use it, run the `bash build_gradle.sh` command from the \< project root \> directory.

## Running the Project

Once the project is built, you can run the compiled code.
I have also made a script to make this easier.
My script assumes that the main program / java file (the one that starts your project) is called `Main.java` and is located in the \< package name \> directory.
To use this script and start your program, simply run the `bash run_main.sh` command.

## Final Note

*This concludes the basic explanation, have a look at the example project to see an example of all that has been mentioned here and to see a few more (slightly advanced) common java-gradle interactions.*
