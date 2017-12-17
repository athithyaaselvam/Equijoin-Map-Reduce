* Once the input file is placed in the Hadoop distributed file system, the main function creates a configuration to create a 
job mapper, reducer which is used to perform equijoin.

* Theinitfunc is used to perform the task of setting up parameters to the job and assigning the mapper reducer class.
* The path of input and output file must be passe das arguments.

Map:

	A new mapper class is created for the join task and it extends the inbuilt class mapper. Inside the class, all the
	whitespaces, newline chars are replaced and placed in a record in the input file. The key is extracted from the 
	created record. Now, we write the key-value pairs. This is the function of the mapper class.

Reduce:

	A new reducer class is created for the the join task and it extends the inbuilt class reducer. A string builder is
	created to perform the join. Then, all the records from the map phase are combined into the string builder using a 
	semicolon. The semicolon acts as temporary variable. We remove the semicolon in the next step. Then the records are 
	split and stored in array inorder for further processing. We run loops to compare each and every tuple. We check for 
	the value that matches, if a match is found we combine them together and write the output. We run anoteher loop till 
	the length of array to avoid duplicates.
	
Running:

package dependencies : Equijoin.cse512

sudo -u <username>  <path_of_hadoop> jar equijoin.jar Equijoin.cse512.Equijoin <HDFSinputFile> <HDFSoutputFile>
