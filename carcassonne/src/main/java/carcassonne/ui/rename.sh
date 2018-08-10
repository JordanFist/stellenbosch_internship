if [ $1 == "java" ]
then
	for i in *.java;
	do 
		cp $i `echo $i | cut -d'.' -f1`.old; 
		rm $i
	done
fi
if [ $1 == "old" ]
then
	for i in *.old;
	do 
		cp $i `echo $i | cut -d'.' -f1`.java; 
		rm $i
	done
fi
