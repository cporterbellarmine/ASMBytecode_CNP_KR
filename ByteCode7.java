/**
 * This class acts as an ASM bytecode compiler that will write a class file that will implement a while loop.
 * 
 * @author Christina Porter
 * @author Kaitlyn Reed
 * @author Rob Kelley
 * @version 09/21/2021
 * ASM Bytecode Library Project
 * 
 */

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Label;

import java.io.*;

public class ByteCode7 {
    
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
     * This is my main method.
     * @param args
     */
    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES); //This ClassWriter will generate my class file structure.
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"WhileLoop", null, "java/lang/Object",null); //creating my main constructor for the file
        
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

            /**
             * This block of code creates a constant 0 and stores it in slot 1
             */
            mv.visitInsn(Opcodes.ICONST_0); //creates constant 0 and stores it on the stack
            mv.visitVarInsn(Opcodes.ISTORE, 1); //takes constant off stack and stores at slot 1

            Label two = new Label(); //creates label one that I will use to jump to
            Label one = new Label(); //creates label two that I will use to jump two

            mv.visitLabel(two); //places the location of label 2

            /**
             * This block of code loads the constant in slot 1 and creates another constant 5 and stores it on the stack
             */
            mv.visitVarInsn(Opcodes.ILOAD, 1); //loads the value stored in slot 1
            mv.visitInsn(Opcodes.ICONST_5); //creates constant 5 and stores it on the stack

            /**
             * This block of code checks to see if the first constant on the stack is greater than the value stored at the second slot. If it is, it jumps to the end.
             * If it is not, a string is printed to the console, increments the counter by 1, then jumps back to the top.
             */
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, one); //jump instruction, if the value stored in slot 1 on the stack is greater than the value stored at slot 2 on the stack, then jump to label one
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); //Sets up a stream to print to the console
            mv.visitLdcInsn("One iteration."); //stores the string to print to the console on the stack
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V"); //prints string stored on stack to the console
            mv.visitIincInsn(1, 1); //increments the value stored in stack value 1 by 1
            mv.visitJumpInsn(Opcodes.GOTO, two); //jump instruction, go to label 2

            mv.visitLabel(one); //places the location of label 1

            /**
             * This block of code is my return statement
             */
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }// end method visitor

        /**
         * This block of code ends the constructor and creates the class file.
         */

        cw.visitEnd(); //ends class

        byte[] b = cw.toByteArray(); //Creates byte array from class

        writeFile(b,"WhileLoop.class"); //Writes the byte array to class file
        
        System.out.println("Done!");

    } //end main

}//end ByteCode7
