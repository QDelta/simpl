package simpl.interpreter;

import simpl.ast.Expr;
import simpl.parser.Parser;
import simpl.parser.SyntaxError;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.TypeError;

import java.io.FileInputStream;
import java.io.InputStream;

public class Interpreter {

    public static void main(String[] args) {
        run(args[0]);
    }

    public static void run(String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr program = (Expr) parseTree.value;

            program.typeCheck(new DefaultTypeEnv());

            State s = State.initialState();
            Value v = program.eval(s);
            System.out.println(v.show(s));
        } catch (SyntaxError e) {
            System.out.println("syntax error");
        } catch (TypeError e) {
            System.out.println("type error");
        } catch (RuntimeError e) {
            System.out.println("runtime error");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
