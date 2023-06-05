package com.tom.tlox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TLox {
    static Boolean hadError = false;

//    public static void main(String[] args) throws IOException {
//        if (args.length > 1) {
//            System.out.println("Usage: tlox [script]");
//            System.exit(64);
//        } else if (args.length == 1) {
//            runFile(args[0]);
//        } else {
//            runPrompt();
//        }
//    }

//    Asp Printer test
//    public static void main(String[] args) throws IOException {
//        Expr expr = new Expr.Binary(
//                new Expr.Unary(
//                        new Token(TokenType.MINUS, "-", null, 1),
//                        new Expr.Literal(123)),
//                new Token(TokenType.STAR_EQUAL, "*=", null, 1),
//                new Expr.Grouping(new Expr.Literal(45.67))
//        );
//    }

//    Rpn Printer test
    public static void main(String[] args) throws IOException {
        Expr expr = new Expr.Binary(
                new Expr.Binary(
                        new Expr.Literal("1"),
                        new Token(TokenType.PLUS, "+", null, 1),
                        new Expr.Literal("2")
                ),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Binary(
                        new Expr.Literal("4"),
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal("3")
                )
        );

        System.out.println(new RpnPrinter().print(expr));
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        // Indicate an error in the exit code
        if (hadError) System.exit(65);
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (; ; ) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false;
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        //Print tokens for now
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
}