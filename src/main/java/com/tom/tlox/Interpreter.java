package com.tom.tlox;

public class Interpreter implements Expr.Visitor<Object> {
    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);

        // TODO: MINUS MINUS AND PLUS PLUS CAN BE BEFORE OR AFTER A VALUE

        switch (expr.operator.type) {
            case MINUS -> {
                return (double) left - (double) right;
            }
            case PLUS -> {
                if (left instanceof Double && right instanceof Double) {
                    return (double) left + (double) right;
                }
                if (left instanceof String && right instanceof String) {
                    return (String) left + (String) right;
                }
            }
            case SLASH -> {
                return (double) left / (double) right;
            }
            case STAR -> {
                return (double) left * (double) right;
            }
        }

        // Unreachable.
        return null;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case MINUS -> {
                return -(double) right;
            }
            case BANG -> {
                return !isTruthy(right);
            }
        }

        // Unreachable.
        return null;
    }

    private boolean isTruthy(Object object) {
        if (object == null) return false;
        if (object instanceof Boolean) return false;
        if (object instanceof String) {
            switch ((String) object) {
                case "false", "0", "nil", "null" -> {
                    return false;
                }
            }
        }
        return !(object instanceof Double) || (double) object != 0.0;
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }
}
