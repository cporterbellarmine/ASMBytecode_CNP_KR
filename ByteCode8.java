/**
 * This class acts as an ASM bytecode compiler that will write a class file that will implement an if..then..else.
 * 
 * @author Christina Porter
 * @author Kaitlyn Reed
 * @author Rob Kelley
 * @version 09/21/2021
 * ASM Bytecode Library Project
 * 
 */

import org.objectweb.asm.*;
import java.io.*;

public class ByteCode8 {

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

    public static void main(String[] args){
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES); //This ClassWriter will generate my class file structure.
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"ForLoop", null, "java/lang/Object",null); //creating my main constructor for the file
        
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
		}//end constructor instantiation

        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null); //visits my main method in the class
            mv.visitCode(); //visits the code inside the method

            Label one = new Label(); //creates label one I will use to jump to
            Label two = new Label(); //creates label two I will use to jump to

            /**
             * This block creates a constant 0 and stores it on slot one
             */
            mv.visitInsn(Opcodes.ICONST_0); //creates a constant 0 and stores that value on the stack
            mv.visitVarInsn(Opcodes.ISTORE, 1); //moves the value from the stack to slot 1

            /**
             * This block of code loads the value from slot 1 and creates another constant to store on the stack, 5.
             */
            mv.visitVarInsn(Opcodes.ILOAD, 1); //loads the value stored in slot 1
            mv.visitInsn(Opcodes.ICONST_5); //creates a new constant and stores it at the top of the stack

            /**
             * This block of code prepares the print stream, stores a value to print, then prints it to the screen.
             */
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, one); //if the first slot on the stack is greater than the second slot, jump to instruction on1
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); //Sets up a stream to print to the console
            mv.visitLdcInsn("The number "); //the string to store on the stack
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V"); //prints string stored on stack to the console and pops from the stack

            /**
             * This block of code prepares the print stream, stores a value to print, then prints it to the screen.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1); //loads the value stored in slot 1
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(I)V"); //prints the value to the screen and pops from the stack

            /**
             * This block of code prepares the print stream, stores a value to print, then prints it to the screen.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn(" is less than or equal to 5.");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");

            mv.visitJumpInsn(Opcodes.GOTO, two); //jump to label 2

            mv.visitLabel(one); //Placeholder for label 1

            /**
             * This block of code prepares the print stream, stores a value to print, then prints it to the screen.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("The number is greater than 5.");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");

            mv.visitLabel(two); //Placeholder for label 2

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

        cw.visitEnd(); //ends class

        byte[] b = cw.toByteArray(); //Creates byte array from class

        writeFile(b,"ForLoop.class"); //Writes the byte array to class file
        
        System.out.println("Done!");
    }//end main
    
}//end ByteCode8
