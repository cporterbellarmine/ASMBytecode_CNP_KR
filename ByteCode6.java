/**
 * This class acts as an ASM bytecode compiler that will write a class file that will get input from the user
 * using the Scanner class and output that input to the console.
 * 
 * @author Christina Porter
 * @author Kaitlyn Reed
 * @author Rob Kelley
 * @version 09/21/2021
 * ASM Bytecode Library Project
 * 
 */

import org.objectweb.asm.ClassWriter; //This will be used as my ClassWriter class.
import org.objectweb.asm.MethodVisitor; //This will be used as my method visitor.
import org.objectweb.asm.Opcodes; //This will be used to access the Opcodes.
import java.io.*; //This is used in the writeFile class.

public class ByteCode6{

    /**
     * This method will create a file with the bytearray that is created from the main method into the specified name.
     * @param bytearray
     * @param fileName
     */
    public static void writeFile(byte[] bytearray, String fileName){

        try{
            FileOutputStream out = new FileOutputStream(fileName);
            out.write(bytearray);
            out.close();
        }//end try
        catch(IOException e){
        System.out.println(e.getMessage());
        }//end catch
        
    }//end writeFile

    /**
     * This is the main method.
     * @param args
     */
    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES); //This ClassWriter will generate my class file structure.
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"ScannerUser", null, "java/lang/Object",null); //creating my main constructor for the file
        
        /**
         * This block of code is creating my constructor.
         */
        {
			MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}//end constructor creation

        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null); //visits my main method in the class
            mv.visitCode(); //visits the code inside the method
            
            /**
             * This block of code sets up my scanner object and stores it
             */
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner"); //Instantiates the scanner object
            mv.visitInsn(Opcodes.DUP); //Duplicates the previous object <Code will not work without this>
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;"); //Sets up a stream to read the next line
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V"); //Places the objet that reads user input on the stack
            mv.visitVarInsn(Opcodes.ASTORE, 1); //Stores the object that reads user input in slot 1 and removes from the stack

            /**
             * This block of code prompts for user input in the form of an int and stores the input that is given by the user.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); //Sets up a stream to print to the console
            mv.visitLdcInsn("Please enter an int:"); //Creates a string and adds it to the stack
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V"); //Prints statement on stack and removes from stack
            mv.visitVarInsn(Opcodes.ALOAD, 1); //Loads the scanner
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false); //Uses the scanner on the stack to get the next int input and stores that input on the stack
            mv.visitVarInsn(Opcodes.ISTORE, 2); //Stores the input in slot 2 and removes from the stack

            /**
             * This block of code prompts for user input in the form of a double and stores the input that is given by the user.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Please enter a double:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextDouble", "()D", false); //Uses the scanner on the stack to get the next double input and stores that input on the stack
            mv.visitVarInsn(Opcodes.DSTORE, 3); //Stores the input in slot 3

            /**
             * This block of code prompts for user input in the form of a long and stores the input that is given by the user.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Please enter a long:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextLong", "()J", false); //Uses the scanner on the stack to get the next int long and stores that input on the stack
            mv.visitVarInsn(Opcodes.LSTORE, 5); //Stores the input in slot 5

            /**
             * This block of code prints the saved int the user input to the screen.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 2); //Loads the int stored in value 2
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false); //invokes the printstream to print the int loaded onto the stack to the screen

            /**
             * This block of code prints the saved double the user input to the screen.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.DLOAD, 3); //Loads the double stored in value 2
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false); //invokes the printstream to print the double loaded onto the stack to the screen

            /**
             * This block of code prints the saved long the user input to the screen.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 5); //Loads the long stored in value 2
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false); //invokes the printstream to print the long loaded onto the stack to the screen

            /**
             * This block of code is my return statement
             */
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }//end method visitor

        /**
         * This block of code ends the constructor and creates the class file.
         */
        cw.visitEnd(); //Ends class

        byte[] b = cw.toByteArray(); //Creates byte array from class

        writeFile(b,"ScannerUser.class"); //Writes the byte array to class file
        
        System.out.println("Done!");

    }//end main
}//end ByteCode6