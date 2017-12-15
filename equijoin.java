package Equijoin.cse512;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
	
public class Equijoin 
{
	
	// class : Mapper - mapping phase.
	public static class MapperClass extends Mapper<LongWritable, Text, LongWritable, Text> 
	{
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			String strtrim = value.toString().replaceAll("\\s",""); 

			// mapperkey parsing which would result in the keys of the set.

			Long keymap = Long.parseLong(strtrim.toString().split(",")[1]); 

			//System.out.println(mapperkey);	// for reference 
			//System.out.println(trimmedValue);	//for reference 
			
			context.write(new LongWritable(keymap), new Text(strtrim)); // writing the key,value pairs
			//System.out.println(strtrim);		//for reference
		}
	}																																			

	// class : Reducer - reduce phase.
	public static class ReducerClass extends Reducer<LongWritable, Text, LongWritable, Text> 
	{
	   protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException 
	   {
		   StringBuilder combinedRec = new StringBuilder(); // empty string.
		   
		   // looping the list for text values.
		   
		   for (Text val : values) 
			{
				combinedRec = combinedRec.append(val.toString()).append(":"); // combine records with :
			}
		   String temp = combinedRec.toString();
		   combinedRec = new StringBuilder(temp.substring(0, combinedRec.length()-1));// removing the last : that will be added

		   //joinedRecord = temp.substring(0, joinedRecord.length()-1); //for reference

		   temp = combinedRec.toString();
		   String[] recArray = temp.split(":"); 
		   
		   // performing the join.

		   for(int i=0; i<recArray.length-1; i++)
		   {
			   for(int j=i+1; j<recArray.length; j++)
			   {
				   if(!recArray[i].split(",")[0].equals(recArray[j].split(",")[0]))
				   {
					   combinedRec = new StringBuilder(recArray[i]); // 1st record added.
					   combinedRec.append(",").append(recArray[j]);  // appending the 2nd record to the matching record.
					   context.write(null, new Text(combinedRec.toString()));
				   }
			   }
		   }
		}
	}
	

	// job initialization
	public static void initFunc(Job job)
	{
		job.setJarByClass(Equijoin.class);
	    job.setMapperClass(MapperClass.class);
	    job.setReducerClass(ReducerClass.class);	    
	    job.setOutputKeyClass(LongWritable.class);
	    job.setOutputValueClass(Text.class);
	    
		
	}
	public static void main(String[] args) throws Exception 
	{
	    Configuration config = new Configuration(); // job assignment config
	    Job job = Job.getInstance(config, "EquiJoin"); // job config with class

	    //calling the initfunc to initialize the job var.

	    Equijoin.initFunc(job);

	    // formatting.

	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));

	    // conditional if to exit
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	 }
}
