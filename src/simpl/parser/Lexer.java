/* The following code was generated by JFlex 1.4.3 on 2022/5/10 下午7:04 */

package simpl.parser;

import java_cup.runtime.Symbol;


/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 2022/5/10 下午7:04 from the specification file
 * <tt>simpl.lex</tt>
 */
class Lexer implements java_cup.runtime.Scanner, Symbols {

    /**
     * This character denotes the end of file
     */
    public static final int YYEOF = -1;
    /**
     * lexical states
     */
    public static final int YYCOMMENT = 2;
    public static final int YYINITIAL = 0;
    /**
     * initial size of the lookahead buffer
     */
    private static final int ZZ_BUFFERSIZE = 16384;
    /**
     * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
     * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
     * at the beginning of a line
     * l is of the form l = 2*k, k a non negative integer
     */
    private static final int[] ZZ_LEXSTATE = {
            0, 0, 1, 1
    };

    /**
     * Translates characters to character classes
     */
    private static final String ZZ_CMAP_PACKED =
            "\11\0\1\6\1\1\1\0\1\6\1\2\22\0\1\6\1\45\3\0" +
                    "\1\36\1\0\1\4\1\7\1\11\1\10\1\33\1\46\1\34\1\0" +
                    "\1\35\12\5\1\43\1\47\1\41\1\40\1\42\2\0\32\4\4\0" +
                    "\1\3\1\0\1\24\1\3\1\20\1\27\1\16\1\17\1\3\1\23" +
                    "\1\13\2\3\1\14\1\3\1\12\1\31\2\3\1\15\1\30\1\22" +
                    "\1\32\1\3\1\21\1\3\1\26\1\25\1\0\1\44\1\0\1\37" +
                    "\uff81\0";

    /**
     * Translates characters to character classes
     */
    private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);
    private static final String ZZ_ACTION_PACKED_0 =
            "\2\0\1\1\2\2\1\3\1\4\1\5\1\6\1\7" +
                    "\14\3\1\10\1\11\1\12\1\13\1\14\1\15\1\16" +
                    "\1\17\1\1\1\20\1\21\1\22\1\23\3\24\1\25" +
                    "\1\26\1\27\2\3\1\30\1\31\5\3\1\32\7\3" +
                    "\1\33\1\3\1\34\1\35\1\36\1\37\1\40\1\41" +
                    "\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51" +
                    "\1\3\1\52\1\53\1\54\11\3\1\55\1\56\1\3" +
                    "\1\57\1\60\1\3\1\61\1\62\3\3\1\63\1\64" +
                    "\2\3\1\65\1\3\1\66\1\67";
    /**
     * Translates DFA states to action switch labels.
     */
    private static final int[] ZZ_ACTION = zzUnpackAction();
    private static final String ZZ_ROWMAP_PACKED_0 =
            "\0\0\0\50\0\120\0\120\0\170\0\240\0\310\0\360" +
                    "\0\u0118\0\120\0\u0140\0\u0168\0\u0190\0\u01b8\0\u01e0\0\u0208" +
                    "\0\u0230\0\u0258\0\u0280\0\u02a8\0\u02d0\0\u02f8\0\120\0\120" +
                    "\0\120\0\120\0\120\0\u0320\0\u0348\0\u0370\0\u0398\0\120" +
                    "\0\120\0\120\0\120\0\120\0\u03c0\0\u03e8\0\120\0\120" +
                    "\0\120\0\u0410\0\u0438\0\u0460\0\240\0\u0488\0\u04b0\0\u04d8" +
                    "\0\u0500\0\u0528\0\240\0\u0550\0\u0578\0\u05a0\0\u05c8\0\u05f0" +
                    "\0\u0618\0\u0640\0\240\0\u0668\0\240\0\120\0\120\0\120" +
                    "\0\120\0\120\0\120\0\120\0\120\0\240\0\240\0\240" +
                    "\0\240\0\240\0\u0690\0\240\0\240\0\240\0\u06b8\0\u06e0" +
                    "\0\u0708\0\u0730\0\u0758\0\u0780\0\u07a8\0\u07d0\0\u07f8\0\u0820" +
                    "\0\240\0\u0848\0\240\0\240\0\u0870\0\240\0\240\0\u0898" +
                    "\0\u08c0\0\u08e8\0\240\0\240\0\u0910\0\u0938\0\240\0\u0960" +
                    "\0\240\0\240";
    /**
     * Translates a state to a row index in the transition table
     */
    private static final int[] ZZ_ROWMAP = zzUnpackRowMap();
    private static final String ZZ_TRANS_PACKED_0 =
            "\1\3\1\4\1\5\1\6\1\3\1\7\1\4\1\10" +
                    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20" +
                    "\1\21\1\22\1\23\1\6\1\24\2\6\1\25\1\6" +
                    "\1\26\1\6\1\27\1\30\1\31\1\32\1\33\1\34" +
                    "\1\35\1\36\1\37\1\40\1\41\1\42\1\43\7\44" +
                    "\1\45\1\46\37\44\51\0\1\4\51\0\3\6\4\0" +
                    "\21\6\22\0\1\7\52\0\1\47\1\50\47\0\1\51" +
                    "\41\0\3\6\4\0\1\6\1\52\15\6\1\53\1\6" +
                    "\20\0\3\6\4\0\1\54\4\6\1\55\13\6\20\0" +
                    "\3\6\4\0\4\6\1\56\5\6\1\57\6\6\20\0" +
                    "\3\6\4\0\4\6\1\60\14\6\20\0\3\6\4\0" +
                    "\1\61\1\6\1\62\16\6\20\0\3\6\4\0\1\63" +
                    "\11\6\1\64\6\6\20\0\3\6\4\0\12\6\1\65" +
                    "\6\6\20\0\3\6\4\0\1\6\1\66\7\6\1\67" +
                    "\7\6\20\0\3\6\4\0\3\6\1\70\5\6\1\71" +
                    "\7\6\20\0\3\6\4\0\1\72\20\6\20\0\3\6" +
                    "\4\0\17\6\1\73\1\6\20\0\3\6\4\0\3\6" +
                    "\1\74\1\6\1\75\13\6\57\0\1\76\45\0\1\77" +
                    "\1\0\1\100\45\0\1\101\47\0\1\102\2\0\1\103" +
                    "\14\0\1\104\50\0\1\105\41\0\3\6\4\0\2\6" +
                    "\1\106\16\6\20\0\3\6\4\0\10\6\1\107\10\6" +
                    "\20\0\3\6\4\0\2\6\1\110\1\111\15\6\20\0" +
                    "\3\6\4\0\10\6\1\112\10\6\20\0\3\6\4\0" +
                    "\13\6\1\113\5\6\20\0\3\6\4\0\5\6\1\114" +
                    "\1\115\12\6\20\0\3\6\4\0\15\6\1\116\3\6" +
                    "\20\0\3\6\4\0\16\6\1\117\2\6\20\0\3\6" +
                    "\4\0\2\6\1\120\16\6\20\0\3\6\4\0\16\6" +
                    "\1\121\2\6\20\0\3\6\4\0\10\6\1\122\10\6" +
                    "\20\0\3\6\4\0\1\6\1\123\17\6\20\0\3\6" +
                    "\4\0\20\6\1\124\20\0\3\6\4\0\4\6\1\125" +
                    "\14\6\20\0\3\6\4\0\15\6\1\126\3\6\20\0" +
                    "\3\6\4\0\4\6\1\127\14\6\20\0\3\6\4\0" +
                    "\14\6\1\130\4\6\20\0\3\6\4\0\4\6\1\131" +
                    "\14\6\20\0\3\6\4\0\16\6\1\132\2\6\20\0" +
                    "\3\6\4\0\4\6\1\133\14\6\20\0\3\6\4\0" +
                    "\11\6\1\134\7\6\20\0\3\6\4\0\2\6\1\135" +
                    "\16\6\20\0\3\6\4\0\4\6\1\136\14\6\20\0" +
                    "\3\6\4\0\1\137\20\6\20\0\3\6\4\0\12\6" +
                    "\1\140\6\6\20\0\3\6\4\0\2\6\1\141\16\6" +
                    "\20\0\3\6\4\0\5\6\1\142\13\6\20\0\3\6" +
                    "\4\0\4\6\1\143\14\6\20\0\3\6\4\0\4\6" +
                    "\1\144\14\6\20\0\3\6\4\0\2\6\1\145\16\6" +
                    "\20\0\3\6\4\0\16\6\1\146\2\6\20\0\3\6" +
                    "\4\0\1\147\20\6\20\0\3\6\4\0\16\6\1\150" +
                    "\2\6\20\0\3\6\4\0\4\6\1\151\14\6\20\0" +
                    "\3\6\4\0\17\6\1\152\1\6\15\0";
    /**
     * The transition table of the DFA
     */
    private static final int[] ZZ_TRANS = zzUnpackTrans();
    /* error codes */
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    /* error messages for the codes above */
    private static final String[] ZZ_ERROR_MSG = {
            "Unkown internal scanner error",
            "Error: could not match input",
            "Error: pushback value was too large"
    };
    private static final String ZZ_ATTRIBUTE_PACKED_0 =
            "\2\0\2\11\5\1\1\11\14\1\5\11\4\1\5\11" +
                    "\2\1\3\11\24\1\10\11\45\1";
    /**
     * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
     */
    private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();
    /**
     * the input device
     */
    private java.io.Reader zzReader;
    /**
     * the current state of the DFA
     */
    private int zzState;
    /**
     * the current lexical state
     */
    private int zzLexicalState = YYINITIAL;
    /**
     * this buffer contains the current text to be matched and is
     * the source of the yytext() string
     */
    private char[] zzBuffer = new char[ZZ_BUFFERSIZE];
    /**
     * the textposition at the last accepting state
     */
    private int zzMarkedPos;
    /**
     * the current text position in the buffer
     */
    private int zzCurrentPos;
    /**
     * startRead marks the beginning of the yytext() string in the buffer
     */
    private int zzStartRead;
    /**
     * endRead marks the last character in the buffer, that has been read
     * from input
     */
    private int zzEndRead;
    /**
     * number of newlines encountered up to the start of the matched text
     */
    private int yyline;
    /**
     * the number of characters up to the start of the matched text
     */
    private int yychar;
    /**
     * the number of characters from the last newline up to the start of the
     * matched text
     */
    private int yycolumn;
    /**
     * zzAtBOL == true <=> the scanner is currently at the beginning of a line
     */
    private boolean zzAtBOL = true;
    /**
     * zzAtEOF == true <=> the scanner is at the EOF
     */
    private boolean zzAtEOF;
    /**
     * denotes if the user-EOF-code has already been executed
     */
    private boolean zzEOFDone;
    /* user code: */
    private int commentCount = 0;

    /**
     * Creates a new scanner
     * There is also a java.io.InputStream version of this constructor.
     *
     * @param in the java.io.Reader to read input from.
     */
    Lexer(java.io.Reader in) {
        this.zzReader = in;
    }

    /**
     * Creates a new scanner.
     * There is also java.io.Reader version of this constructor.
     *
     * @param in the java.io.Inputstream to read input from.
     */
    Lexer(java.io.InputStream in) {
        this(new java.io.InputStreamReader(in));
    }

    private static int[] zzUnpackAction() {
        int[] result = new int[106];
        int offset = 0;
        offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAction(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }

    private static int[] zzUnpackRowMap() {
        int[] result = new int[106];
        int offset = 0;
        offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackRowMap(String packed, int offset, int[] result) {
        int i = 0;  /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int high = packed.charAt(i++) << 16;
            result[j++] = high | packed.charAt(i++);
        }
        return j;
    }

    private static int[] zzUnpackTrans() {
        int[] result = new int[2440];
        int offset = 0;
        offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackTrans(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            value--;
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }

    private static int[] zzUnpackAttribute() {
        int[] result = new int[106];
        int offset = 0;
        offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAttribute(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }

    /**
     * Unpacks the compressed character translation table.
     *
     * @param packed the packed character translation table
     * @return the unpacked character translation table
     */
    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[0x10000];
        int i = 0;  /* index in packed string  */
        int j = 0;  /* index in unpacked array */
        while (i < 122) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do map[j++] = value; while (--count > 0);
        }
        return map;
    }

    private Symbol token(int tag) {
        return new Symbol(tag, yyline, yycolumn);
    }

    private Symbol token(int tag, Object value) {
        return new Symbol(tag, yyline, yycolumn, value);
    }

    /**
     * Refills the input buffer.
     *
     * @return <code>false</code>, iff there was new input.
     * @throws java.io.IOException if any I/O-Error occurs
     */
    private boolean zzRefill() throws java.io.IOException {

        /* first: make room (if you can) */
        if (zzStartRead > 0) {
            System.arraycopy(zzBuffer, zzStartRead,
                    zzBuffer, 0,
                    zzEndRead - zzStartRead);

            /* translate stored positions */
            zzEndRead -= zzStartRead;
            zzCurrentPos -= zzStartRead;
            zzMarkedPos -= zzStartRead;
            zzStartRead = 0;
        }

        /* is the buffer big enough? */
        if (zzCurrentPos >= zzBuffer.length) {
            /* if not: blow it up */
            char[] newBuffer = new char[zzCurrentPos * 2];
            System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
            zzBuffer = newBuffer;
        }

        /* finally: fill the buffer with new input */
        int numRead = zzReader.read(zzBuffer, zzEndRead,
                zzBuffer.length - zzEndRead);

        if (numRead > 0) {
            zzEndRead += numRead;
            return false;
        }
        // unlikely but not impossible: read 0 characters, but not at end of stream
        if (numRead == 0) {
            int c = zzReader.read();
            if (c == -1) {
                return true;
            } else {
                zzBuffer[zzEndRead++] = (char) c;
                return false;
            }
        }

        // numRead < 0
        return true;
    }


    /**
     * Closes the input stream.
     */
    public final void yyclose() throws java.io.IOException {
        zzAtEOF = true;            /* indicate end of file */
        zzEndRead = zzStartRead;  /* invalidate buffer    */

        if (zzReader != null)
            zzReader.close();
    }


    /**
     * Resets the scanner to read from a new input stream.
     * Does not close the old reader.
     * <p>
     * All internal variables are reset, the old input stream
     * <b>cannot</b> be reused (internal buffer is discarded and lost).
     * Lexical state is set to <tt>ZZ_INITIAL</tt>.
     *
     * @param reader the new input stream
     */
    public final void yyreset(java.io.Reader reader) {
        zzReader = reader;
        zzAtBOL = true;
        zzAtEOF = false;
        zzEOFDone = false;
        zzEndRead = zzStartRead = 0;
        zzCurrentPos = zzMarkedPos = 0;
        yyline = yychar = yycolumn = 0;
        zzLexicalState = YYINITIAL;
    }


    /**
     * Returns the current lexical state.
     */
    public final int yystate() {
        return zzLexicalState;
    }


    /**
     * Enters a new lexical state
     *
     * @param newState the new lexical state
     */
    public final void yybegin(int newState) {
        zzLexicalState = newState;
    }


    /**
     * Returns the text matched by the current regular expression.
     */
    public final String yytext() {
        return new String(zzBuffer, zzStartRead, zzMarkedPos - zzStartRead);
    }


    /**
     * Returns the character at position <tt>pos</tt> from the
     * matched text.
     * <p>
     * It is equivalent to yytext().charAt(pos), but faster
     *
     * @param pos the position of the character to fetch.
     *            A value from 0 to yylength()-1.
     * @return the character at position pos
     */
    public final char yycharat(int pos) {
        return zzBuffer[zzStartRead + pos];
    }


    /**
     * Returns the length of the matched text region.
     */
    public final int yylength() {
        return zzMarkedPos - zzStartRead;
    }


    /**
     * Reports an error that occured while scanning.
     * <p>
     * In a wellformed scanner (no or only correct usage of
     * yypushback(int) and a match-all fallback rule) this method
     * will only be called with things that "Can't Possibly Happen".
     * If this method is called, something is seriously wrong
     * (e.g. a JFlex bug producing a faulty scanner etc.).
     * <p>
     * Usual syntax/scanner level error handling should be done
     * in error fallback rules.
     *
     * @param errorCode the code of the errormessage to display
     */
    private void zzScanError(int errorCode) {
        String message;
        try {
            message = ZZ_ERROR_MSG[errorCode];
        } catch (ArrayIndexOutOfBoundsException e) {
            message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
        }

        throw new Error(message);
    }


    /**
     * Pushes the specified amount of characters back into the input stream.
     * <p>
     * They will be read again by then next call of the scanning method
     *
     * @param number the number of characters to be read again.
     *               This number must not be greater than yylength()!
     */
    public void yypushback(int number) {
        if (number > yylength())
            zzScanError(ZZ_PUSHBACK_2BIG);

        zzMarkedPos -= number;
    }


    /**
     * Contains user EOF-code, which will be executed exactly once,
     * when the end of file is reached
     */
    private void zzDoEOF() throws java.io.IOException {
        if (!zzEOFDone) {
            zzEOFDone = true;
            yyclose();
        }
    }


    /**
     * Resumes scanning until the next regular expression is matched,
     * the end of input is encountered or an I/O-Error occurs.
     *
     * @return the next token
     * @throws java.io.IOException if any I/O-Error occurs
     */
    public java_cup.runtime.Symbol next_token() throws java.io.IOException {
        int zzInput;
        int zzAction;

        // cached fields:
        int zzCurrentPosL;
        int zzMarkedPosL;
        int zzEndReadL = zzEndRead;
        char[] zzBufferL = zzBuffer;
        char[] zzCMapL = ZZ_CMAP;

        int[] zzTransL = ZZ_TRANS;
        int[] zzRowMapL = ZZ_ROWMAP;
        int[] zzAttrL = ZZ_ATTRIBUTE;

        while (true) {
            zzMarkedPosL = zzMarkedPos;

            boolean zzR = false;
            for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                 zzCurrentPosL++) {
                switch (zzBufferL[zzCurrentPosL]) {
                    case '\u000B':
                    case '\u000C':
                    case '\u0085':
                    case '\u2028':
                    case '\u2029':
                        yyline++;
                        yycolumn = 0;
                        zzR = false;
                        break;
                    case '\r':
                        yyline++;
                        yycolumn = 0;
                        zzR = true;
                        break;
                    case '\n':
                        if (zzR)
                            zzR = false;
                        else {
                            yyline++;
                            yycolumn = 0;
                        }
                        break;
                    default:
                        zzR = false;
                        yycolumn++;
                }
            }

            if (zzR) {
                // peek one character ahead if it is \n (if we have counted one line too much)
                boolean zzPeek;
                if (zzMarkedPosL < zzEndReadL)
                    zzPeek = zzBufferL[zzMarkedPosL] == '\n';
                else if (zzAtEOF)
                    zzPeek = false;
                else {
                    boolean eof = zzRefill();
                    zzEndReadL = zzEndRead;
                    zzMarkedPosL = zzMarkedPos;
                    zzBufferL = zzBuffer;
                    if (eof)
                        zzPeek = false;
                    else
                        zzPeek = zzBufferL[zzMarkedPosL] == '\n';
                }
                if (zzPeek) yyline--;
            }
            zzAction = -1;

            zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

            zzState = ZZ_LEXSTATE[zzLexicalState];


            zzForAction:
            {
                while (true) {

                    if (zzCurrentPosL < zzEndReadL)
                        zzInput = zzBufferL[zzCurrentPosL++];
                    else if (zzAtEOF) {
                        zzInput = YYEOF;
                        break zzForAction;
                    } else {
                        // store back cached positions
                        zzCurrentPos = zzCurrentPosL;
                        zzMarkedPos = zzMarkedPosL;
                        boolean eof = zzRefill();
                        // get translated positions and possibly new buffer
                        zzCurrentPosL = zzCurrentPos;
                        zzMarkedPosL = zzMarkedPos;
                        zzBufferL = zzBuffer;
                        zzEndReadL = zzEndRead;
                        if (eof) {
                            zzInput = YYEOF;
                            break zzForAction;
                        } else {
                            zzInput = zzBufferL[zzCurrentPosL++];
                        }
                    }
                    int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
                    if (zzNext == -1) break zzForAction;
                    zzState = zzNext;

                    int zzAttributes = zzAttrL[zzState];
                    if ((zzAttributes & 1) == 1) {
                        zzAction = zzState;
                        zzMarkedPosL = zzCurrentPosL;
                        if ((zzAttributes & 8) == 8) break zzForAction;
                    }

                }
            }

            // store back cached position
            zzMarkedPos = zzMarkedPosL;

            switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
                case 2: { /* skip */
                }
                case 56:
                    break;
                case 49: {
                    return token(TRUE);
                }
                case 57:
                    break;
                case 37: {
                    return token(NIL);
                }
                case 58:
                    break;
                case 25: {
                    return token(IF);
                }
                case 59:
                    break;
                case 47: {
                    return token(CASE);
                }
                case 60:
                    break;
                case 46: {
                    return token(ELSE);
                }
                case 61:
                    break;
                case 16: {
                    return token(OR);
                }
                case 62:
                    break;
                case 24: {
                    return token(IN);
                }
                case 63:
                    break;
                case 50: {
                    return token(THEN);
                }
                case 64:
                    break;
                case 13: {
                    return token(EQ);
                }
                case 65:
                    break;
                case 54: {
                    return token(ORELSE);
                }
                case 66:
                    break;
                case 33: {
                    return token(ASSIGN);
                }
                case 67:
                    break;
                case 17: {
                    return token(DEREF);
                }
                case 68:
                    break;
                case 52: {
                    return token(WHILE);
                }
                case 69:
                    break;
                case 6: {
                    return token(MUL);
                }
                case 70:
                    break;
                case 1: {
                    throw new SyntaxError("Illegal character " + yytext(), yyline, yycolumn);
                }
                case 71:
                    break;
                case 31: {
                    return token(NE);
                }
                case 72:
                    break;
                case 48: {
                    return token(WITH);
                }
                case 73:
                    break;
                case 45: {
                    return token(LAZY);
                }
                case 74:
                    break;
                case 55: {
                    return token(ANDALSO);
                }
                case 75:
                    break;
                case 23: {
                    throw new SyntaxError("Comment mismatch, extra *) found", yyline, yycolumn);
                }
                case 76:
                    break;
                case 19: {
                    return token(SEMI);
                }
                case 77:
                    break;
                case 43: {
                    return token(REC);
                }
                case 78:
                    break;
                case 32: {
                    return token(GE);
                }
                case 79:
                    break;
                case 30: {
                    return token(LE);
                }
                case 80:
                    break;
                case 5: {
                    return token(LPAREN);
                }
                case 81:
                    break;
                case 39: {
                    return token(INL);
                }
                case 82:
                    break;
                case 8: {
                    return token(ADD);
                }
                case 83:
                    break;
                case 44: {
                    return token(END);
                }
                case 84:
                    break;
                case 28: {
                    return token(OF);
                }
                case 85:
                    break;
                case 18: {
                    return token(COMMA);
                }
                case 86:
                    break;
                case 38: {
                    return token(NOT);
                }
                case 87:
                    break;
                case 34: {
                    return token(CONS);
                }
                case 88:
                    break;
                case 51: {
                    return token(FALSE);
                }
                case 89:
                    break;
                case 10: {
                    return token(DIV);
                }
                case 90:
                    break;
                case 15: {
                    return token(GT);
                }
                case 91:
                    break;
                case 21: {
                    commentCount = 1;
                    yybegin(YYCOMMENT);
                }
                case 92:
                    break;
                case 14: {
                    return token(LT);
                }
                case 93:
                    break;
                case 11: {
                    return token(MOD);
                }
                case 94:
                    break;
                case 4: {
                    return token(NUM, Integer.valueOf(yytext()));
                }
                case 95:
                    break;
                case 3: {
                    return token(ID, yytext());
                }
                case 96:
                    break;
                case 9: {
                    return token(SUB);
                }
                case 97:
                    break;
                case 40: {
                    return token(INR);
                }
                case 98:
                    break;
                case 53: {
                    return token(LAZYFN);
                }
                case 99:
                    break;
                case 36: {
                    commentCount--;
                    if (commentCount == 0) yybegin(YYINITIAL);
                }
                case 100:
                    break;
                case 35: {
                    commentCount++;
                }
                case 101:
                    break;
                case 7: {
                    return token(RPAREN);
                }
                case 102:
                    break;
                case 26: {
                    return token(FN);
                }
                case 103:
                    break;
                case 12: {
                    return token(NEG);
                }
                case 104:
                    break;
                case 27: {
                    return token(DO);
                }
                case 105:
                    break;
                case 22: {
                    return token(UNIT);
                }
                case 106:
                    break;
                case 42: {
                    return token(REF);
                }
                case 107:
                    break;
                case 29: {
                    return token(ARROW);
                }
                case 108:
                    break;
                case 41: {
                    return token(LET);
                }
                case 109:
                    break;
                case 20: {
                }
                case 110:
                    break;
                default:
                    if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
                        zzAtEOF = true;
                        zzDoEOF();
                        {
                            if (yystate() == YYCOMMENT) {
                                throw new SyntaxError("Comment mismatch, need *) at EOF", yyline, yycolumn);
                            }
                            return token(EOF, null);
                        }
                    } else {
                        zzScanError(ZZ_NO_MATCH);
                    }
            }
        }
    }


}