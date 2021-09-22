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

import org.objectweb.asm.*;
import java.io.*;

public class ByteCode9 {

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
     * This is my main method
     * @param args
     */
    public static void main(String[] args){


        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES); //This ClassWriter will generate my class file structure.
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"Accumulator", null, "java/lang/Object",null); //creating my main constructor for the file

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
             * This block of code sets up my scanner object and stores it
             */
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner"); //Instantiates the scanner object
            mv.visitInsn(Opcodes.DUP); //Duplicates the previous object <Code will not work without this>
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;"); //Sets up a stream to read the next line
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V"); //Places the objet that reads user input on the stack
            mv.visitVarInsn(Opcodes.ASTORE, 1); //Stores the object that reads user input in slot 1 and removes from the stack

            /**
             * This block of code creates a constant on the stack and stores the value
             */
            mv.visitInsn(Opcodes.ICONST_0); //creates constant 0 and stores it on the stack
            mv.visitVarInsn(Opcodes.ISTORE, 2); //stores the value on the stack in slot 2

            /**
             * This block of code creates a constant on the stack and stores the value
             */
            mv.visitInsn(Opcodes.ICONST_0); //creates constant 0 and stores it on the stack
            mv.visitVarInsn(Opcodes.ISTORE, 3); //stores the value on the stack in slot 3

            mv.visitLabel(two); //placeholder for label two

            /**
             * This block of code loads a stored value and places it on the stack, then loads another constant to the stack
             */
            mv.visitVarInsn(Opcodes.ILOAD, 2); //loads the value stored in slot 2 onto the stack
            mv.visitInsn(Opcodes.ICONST_5); //creates a new constant 5 and stores it on the stack

            /**
             * This block of code compares the two top values on the stack, and if the first is greater than the second, it jumps to the end.
             * If the first is less than the second, it prints to the console and asks to enter another int.
             */
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, one); //jump instruction, if the value stored in slot 1 on the stack is greater than the value stored at slot 2 on the stack, then jump to label one
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); //Sets up a stream to print to the console
            mv.visitLdcInsn("Please enter an int to add to "); //the string to store on the stack
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V"); //prints string stored on stack to the console and pops value from stack

            /**
             * This block of prints an int to the screen.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 3); //loads value stored at 3 onto stack
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(I)V"); //prints value stored on stack to the console and pops value from stack

            /**
             * This block of code prints a string to the screen.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn(".");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");

            /**
             * This block of code loads the scanner and scans for the input value, storing it on the stack.
             */
            mv.visitVarInsn(Opcodes.ALOAD, 1); //loads the value stored in spot 1 to the stack
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I"); //uses the scanner on the stack to scan for the next line input and place it on the stack
            mv.visitVarInsn(Opcodes.ISTORE, 4); //stores the input into spot 4 and pops this to the stack

            /**
             * This block of code loads to values to the top of the stack and adds them together and stores the new value.
             */
            mv.visitVarInsn(Opcodes.ILOAD, 3); //load value 3 to the stack
            mv.visitVarInsn(Opcodes.ILOAD, 4); //load value 4 to the stack
            mv.visitInsn(Opcodes.IADD); //add the top two values on the stack together and places this value on the stack
            mv.visitVarInsn(Opcodes.ISTORE, 3); //store the value on the stack in slot 3

            /**
             * This block of code prints a string to the console.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("The total is ");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V");

            /**
             * This block of code prints an int to the console.
             */
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V");
            
            mv.visitIincInsn(2, 1); //increments the value stored in slot 2 by 1

            mv.visitJumpInsn(Opcodes.GOTO, two); //jump to label two

            mv.visitLabel(one); //placeholder for label one

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

        writeFile(b,"Accumulator.class"); //Writes the byte array to class file
        
        System.out.println("Done!");
    }//end main

    
}//end ByteCode9
