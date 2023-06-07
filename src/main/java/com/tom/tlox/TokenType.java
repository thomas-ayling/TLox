package com.tom.tlox;

public enum TokenType {
    // Single-character tokens.
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, SEMICOLON,


    // One or two character tokens.
    PERCENT,
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,
    SLASH, SLASH_SLASH, SLASH_EQUAL, SLASH_BACKSLASH,
    STAR, STAR_STAR, STAR_EQUAL,
    MINUS, MINUS_MINUS, MINUS_EQUAL,
    PLUS, PLUS_PLUS, PLUS_EQUAL,

    // Literals.
    IDENTIFIER, STRING, NUMBER,

    // Keywords.
    AND, CLASS, ELSE, FALSE, FUN, FOR, IF, NIL, OR,
    PRINT, RETURN, SUPER, THIS, TRUE, VAR, WHILE,

    EOF
}