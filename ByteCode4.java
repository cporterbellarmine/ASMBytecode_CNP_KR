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

import org.objectweb.asm.*;

public class ByteCode4 {
	
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
	    cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"Compare", null, "java/lang/Object",null); //creating my main constructor for the file
	    
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
             * This block of code compares two double values and returns a 1 if the first value is greater
             * or a -1 if the second value is greater.
             */
	        mv.visitLdcInsn((Double)173.00); // initializes the first double
	        mv.visitVarInsn(Opcodes.DSTORE,1); // stores the first double on the stack
	        mv.visitLdcInsn((Double)45.00); // initializes the second double
	        mv.visitVarInsn(Opcodes.DSTORE,3); // stores the second double on the stack
	        mv.visitVarInsn(Opcodes.DLOAD,1); // loads first double
	        mv.visitVarInsn(Opcodes.DLOAD,3); // loads second double
	        mv.visitInsn(Opcodes.DCMPL); // compares the size of the doubles
	        mv.visitVarInsn(Opcodes.ISTORE,5); // stores 1 or -1 (in this case, 1)
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // sets up stream to write the next line
	        mv.visitVarInsn(Opcodes.ILOAD, 5); // loads the 1 to printed
	        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false); // prints the 1
	        
	        /**
             * This block of code WAS MEANT TO compare two integer values and returns a True if the values are equal and returns
             * false if they aren't equal.
             */
            /**Label one = new Label();
	        mv.visitLdcInsn((Integer)173);
	        mv.visitVarInsn(Opcodes.ISTORE,7);
	        mv.visitLdcInsn((Integer)45);
	        mv.visitVarInsn(Opcodes.ISTORE,9);
	        mv.visitVarInsn(Opcodes.ILOAD,7);
	        mv.visitVarInsn(Opcodes.ILOAD,9);
	        mv.visitJumpInsn(Opcodes.IF_ICMPNE, one); //<<<<< I had issues with this step.
	        mv.visitLdcInsn("True");
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
	        mv.visitVarInsn(Opcodes.ILOAD, 11);
	        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            mv.visitLabel(one);
            mv.visitLdcInsn("False");
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
	        mv.visitVarInsn(Opcodes.ILOAD, 12);
	        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            */
	        
	        /**
             * This block of code compares two long values and returns a 1 if the first value is greater
             * or a -1 if the second value is greater.
             */
	        mv.visitLdcInsn((long)1000000000); // instantiates the first long
	        mv.visitVarInsn(Opcodes.LSTORE,13); // stores the first long on the stack
	        mv.visitLdcInsn((long)1234567891);// instantiates the second long
	        mv.visitVarInsn(Opcodes.LSTORE,15); // stores the second long on the stack
	        mv.visitVarInsn(Opcodes.LLOAD,13); // loads first long
	        mv.visitVarInsn(Opcodes.LLOAD,15);// loads second long
	        mv.visitInsn(Opcodes.LCMP);  // compares and returns -1 or 1 (in this case, -1)
	        mv.visitVarInsn(Opcodes.ISTORE,17); // stores -1
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // sets up stream to print the next line
	        mv.visitVarInsn(Opcodes.ILOAD, 17); // loads -1 to be printed
	        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false); // prints -1
	        
	        /**
             * This block of code is my return statement
             */
	        mv.visitInsn(Opcodes.RETURN);
	        mv.visitMaxs(0,0);
	        mv.visitEnd();
	    } // end MethodVisitor

	    cw.visitEnd(); //Ends class

        byte[] b = cw.toByteArray(); //Creates byte array from class

        writeFile(b,"Compare.class"); //Writes the byte array to class file
        
        System.out.println("Done!");

		} // end main

} // end ByteCode4
