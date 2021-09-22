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

import java.io.FileOutputStream; // These are both used for the writeFile method.
import java.io.IOException;

import org.objectweb.asm.ClassWriter; // This will be used as my ClassWriter class.
import org.objectweb.asm.MethodVisitor; //This will be used as my method visitor.
import org.objectweb.asm.Opcodes; //This will be used to access the Opcodes.

public class ByteCode3 {
	
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
        } // end try
        catch(IOException e){
        System.out.println(e.getMessage());
        } // end catch
        
    } //end writeFile
	
	/**
     * This is the main method.
     * @param args
     */
	public static void main(String args[]) {

		
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES); //This ClassWriter will generate my class file structure.
	    cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"Divide", null, "java/lang/Object",null); //creating my main constructor for the file
	    
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
		} // end construction creation

	    {
	        MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null); //visits my main method in the class
	        mv.visitCode(); //visits the code inside the method
	        
	        /**
             * This block of code takes an initial double and divides it by a second double.
             */
	        mv.visitLdcInsn((Double)173.43); // initializes the first double
	        mv.visitVarInsn(Opcodes.DSTORE,1); // stores the first double
	        mv.visitLdcInsn((Double)45.56); // initializes the second double
	        mv.visitVarInsn(Opcodes.DSTORE,3); // stores the second double
	        mv.visitVarInsn(Opcodes.DLOAD,1); // loads the first double
	        mv.visitVarInsn(Opcodes.DLOAD,3); // loads the second double
	        mv.visitInsn(Opcodes.DDIV); // divides the first double by the second
	        mv.visitVarInsn(Opcodes.DSTORE,5); // stores the value
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // sets up stream to write next line
	        mv.visitVarInsn(Opcodes.DLOAD, 5); // loads the value to be printed
	        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false); // prints the vlaue
	        
	        /**
             * This block of code takes an initial long and divides it by a second long.
             */
	        mv.visitLdcInsn((long)1234567891); // initializes the first long
	        mv.visitVarInsn(Opcodes.LSTORE,7); // stores the first long in the stack
	        mv.visitLdcInsn((long)1234567891); // initializes the second long
	        mv.visitVarInsn(Opcodes.LSTORE,9); // stores the second long in the stack
	        mv.visitVarInsn(Opcodes.LLOAD,7); // loads the first long
	        mv.visitVarInsn(Opcodes.LLOAD,9); // loads the second long
	        mv.visitInsn(Opcodes.LDIV);  // divides the first long by the second one
	        mv.visitVarInsn(Opcodes.LSTORE,11); // stores that value
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // sets up the stream to print the next line
	        mv.visitVarInsn(Opcodes.LLOAD, 11); // loads the value to be printed
	        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false); // prints the value
	        
	        /**
             * This block of code takes an initial integer and divides it by a second integer.
             */
	        mv.visitLdcInsn((Integer)15); // initializes the first integer
	        mv.visitVarInsn(Opcodes.ISTORE,13); // stores the first integer
	        mv.visitLdcInsn((Integer)5); // initializes the second integer
	        mv.visitVarInsn(Opcodes.ISTORE,15); // stores the second integer
	        mv.visitVarInsn(Opcodes.ILOAD,13); // loads the first integer
	        mv.visitVarInsn(Opcodes.ILOAD,15); // loads the second integer
	        mv.visitInsn(Opcodes.IDIV); // divides the first integer by the second integer and records the value
	        mv.visitVarInsn(Opcodes.ISTORE,17); // stores the value
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // sets up the stream to print the next line
	        mv.visitVarInsn(Opcodes.ILOAD, 17); // loads the value to be printed
	        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false); // prints the value
	        
	        /**
             * This block of code is my return statement
             */
	        mv.visitInsn(Opcodes.RETURN);
	        mv.visitMaxs(0,0);
	        mv.visitEnd();
	        
	    } // end method visitor

	    /**
         * This block of code ends the constructor and creates the class file.
         */
        cw.visitEnd(); //Ends class

        byte[] b = cw.toByteArray(); //Creates byte array from class

        writeFile(b,"Divide.class"); //Writes the byte array to class file
        
        System.out.println("Done!");
			
		} // end main
	
} // end ByteCode3
