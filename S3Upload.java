// Program to Sync S3 bucket with local folder

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class S3Upload {
    public static void main(String[] args) throws IOException {
        // Writing out paths and commands
        String bucketPath = "s3://some-s3-bucket"; // Replace with your s3 bucket
        String localFolder = "C:\\Users\\YourProfile\\Documents"; // Replace with your local folder that you want to sync
        // Path of AWS cli bin folder (Check that you have it installed) if it is a different folder update it
        String awsCMDPath = "C:\\Program Files\\Amazon\\AWSCLI\\bin";
        String options = "--delete"; // Used to remove files that are not in local folder
        // Create String array to store each argument for the s3 command
        // command for awscli s3 argument at terminal: aws s3 sync <source> <target> [--options]
        List<String> cmds = new ArrayList<String>();
        // Append each command to string
        cmds.add("cmd"); // initialize the command prompt first
        cmds.add("/C");
        cmds.add("aws"); // aws command
        cmds.add("s3"); // s3 command
        cmds.add("sync"); // sync operation
        cmds.add(localFolder); // Add folder to be synced from (local folder)
        cmds.add(bucketPath); // path to bucket where the operation will be carried out
        cmds.add(options);
        // Start a new process for sync execution
        ProcessBuilder pb = new ProcessBuilder(cmds);
        // Checking the command in list and printing commands
        System.out.println("command: " + pb.command() + "\n");
        // Working directory (directory of the aws.cmd file)
        pb.directory(new File(awsCMDPath));
        Process p = pb.start();
        // Exception to catch system interrupt
        try {
            p.waitFor(); // wait until the process is finished
            // Get the command line output with code below.
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            System.out.println("Output of command line below:");
            while ((line = br.readLine()) != null) {
                System.out.println(line); // Print output of command line
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
