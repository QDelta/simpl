package simpl.interpreter;

import simpl.ast.Expr;
import simpl.parser.Parser;
import simpl.parser.SyntaxError;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.TypeError;

import java.io.FileInputStream;
import java.io.InputStream;

public class Tester {

    public static void main(String[] args) {
        interpret("doc/examples/plus.spl");
        interpret("doc/examples/factorial.spl");
        interpret("doc/examples/gcd1.spl");
        interpret("doc/examples/gcd2.spl");
        interpret("doc/examples/max.spl");
        interpret("doc/examples/sum.spl");
        interpret("doc/examples/map.spl");
        interpret("doc/examples/pcf.sum.spl");
        interpret("doc/examples/pcf.even.spl");
        interpret("doc/examples/pcf.minus.spl");
        interpret("doc/examples/pcf.factorial.spl");
        interpret("doc/examples/pcf.fibonacci.spl");
        interpret("doc/examples/pcf.twice.spl");
        interpret("doc/examples/new.case.spl");
        interpret("doc/examples/new.mutualrec.spl");
        interpret("doc/examples/new.equality.spl");
        interpret("doc/examples/new.fold.spl");
        interpret("doc/examples/new.lazy1.spl");
        interpret("doc/examples/new.lazy2.spl");
        interpret("doc/examples/new.primes.spl");
        interpret("doc/examples/new.8queen.spl");
    }

    private static void interpret(String filename) {
        System.out.println(filename);
        run(filename);
    }

    public static void run(String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr program = (Expr) parseTree.value;

            System.out.println(program.typeCheck(new DefaultTypeEnv()));

            State s = State.initialState();
            Value v = program.eval(s);
            System.out.println(v.show(s));
        } catch (SyntaxError e) {
            System.out.println("syntax error");
        } catch (TypeError e) {
            System.out.println("type error");
            e.printStackTrace(System.err);
        } catch (RuntimeError e) {
            System.out.println("runtime error");
            e.printStackTrace(System.err);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
