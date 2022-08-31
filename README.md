<p style="text-align:center;font-size:2em"><b>CS383 Course Project Report</b></p>

[TOC]

### Introduction

In this project I implement an interpreter for a ML dialect SimPL.



### Implementation

The implementation is pretty straightforward given the specification and the skeleton, but there are some points that are worth mentioning.



#### Type checker

For type checker I refers to [this blog](https://okmij.org/ftp/ML/generalization.html#levels) for most of my implementation, which demonstrates the development of Rémy's algorithm for OCaml type inference.

The T-LetPoly rule is simple but rather inefficient for implementation, so explicit polymorphic types should be introduced, which brings the procedure of type generalization and instantiation.

The instantiation is just replacing all type quantifiers with fresh type variables, but the generalization should be taken care of since type variables still in the context are not supposed to be generalized since generalization here will break the constraints.

The naive solution is scanning the environment, but Rémy came up with a more efficient way introducing generalization levels, relaxing environment traversing to integer comparison. The original idea comes from the similarity between memory management and type generalization (ownership of memory region/type variable).



#### Equality type constraint

Actually we are going to implement a special case of type class, supporting ad hoc polymorphism for equality check.

A common technique to implement type classes is dictionary passing style, where each type records what classes it belongs to.

But the original definition with equality type have a bit problem. The T-NIL rule:

$$
\frac{}{\Gamma\vdash \texttt{nil}:\alpha\ \texttt{list}}
$$

According to that rule, the type of $\texttt{nil}$ is always a list of an equality type, which makes the construction of list of other types impossible, and the definition of common list operations on arbitrary element impossible, like `fold` and `map`.

So I relax this constraint, requiring it to be equality type only when it is compared to other expressions. It is also the usual way how type classes are implemented.



### Extra features

I also implement some extra features which are common in functional programming languages.



#### Sum type

The classical sum type.

Syntax:

$$
\begin{align*}
e ::= &\ \cdots\\
  |\ \ &\texttt{inl}\ e\\
  |\ \ &\texttt{inr}\ e\\
  |\ \ &\texttt{case}\ e\ \texttt{of}\ \texttt{inl}\ x\ \texttt{=>}\ e\ |\ \texttt{inr}\ x\ \texttt{=>}\ e
\end{align*}
$$

Type definition:

$$
\begin{align*}
t ::= &\ \cdots\\
  |\ \ &t+t
\end{align*}
$$

Typing rules:

$$
\frac{\Gamma\vdash e:t_1}{\Gamma\vdash\texttt{inl}\ e:t_1+t_2}
\qquad
\frac{\Gamma\vdash e:t_2}{\Gamma\vdash\texttt{inr}\ e:t_1+t_2}
$$

$$
\frac{\Gamma\vdash e_1:t_1+t_2\quad\Gamma[x_1:t_1]\vdash e_2:t_3\quad\Gamma[x_2:t_2]\vdash e_3:t_3}
{\Gamma\vdash\texttt{case}\ e_1\ \texttt{of}\ \texttt{inl}\ x_1\ \texttt{=>}\ e_2\ |\ \texttt{inr}\ x_2\ \texttt{=>}\ e_3:t_3}
$$

Value definition:

$$
V_{t_1+t_2}=(\{\texttt{inl}\}\times V_{t_1})\cup(\{\texttt{inr}\}\times V_{t_2})
$$

Evaluation rules:

$$
\frac{E,M,p;e\Downarrow M',p';v}{E,M,p;\texttt{inl}\ e\Downarrow M',p';(\texttt{inl},v)}
\qquad
\frac{E,M,p;e\Downarrow M',p';v}{E,M,p;\texttt{inr}\ e\Downarrow M',p';(\texttt{inr},v)}
$$

$$
\frac{E,M,p;e_1\Downarrow M',p';(\texttt{inl},v_1)\quad E[x_1\mapsto v_1],M',p';e_2\Downarrow M'',p'';v_2}
{E,M,p;\texttt{case}\ e_1\ \texttt{of}\ \texttt{inl}\ x_1\ \texttt{=>}\ e_2\ |\ \texttt{inr}\ x_2\ \texttt{=>}\ e_3\Downarrow M'',p'';v_2}
$$

$$
\frac{E,M,p;e_1\Downarrow M',p';(\texttt{inr},v_1)\quad E[x_2\mapsto v_1],M',p';e_3\Downarrow M'',p'';v_2}
{E,M,p;\texttt{case}\ e_1\ \texttt{of}\ \texttt{inl}\ x_1\ \texttt{=>}\ e_2\ |\ \texttt{inr}\ x_2\ \texttt{=>}\ e_3\Downarrow M'',p'';v_2}
$$

Example:

```ocaml
(* new.case.spl *)
let f = fn b => fn x => fn y =>
  if b then inl x else inr y
in
  (f true () 1, f false () 1)
end
(* : (unit + int) * (unit + int)
   ==> pair@inl@unit@inr@1 *)
```



#### Case on list

After relaxing the type constraint on $\texttt{nil}$, case expressions on lists is also necessary for defining list operations on arbitrary elements since expressions like $l=\texttt{nil}$ also require an equality type.

Syntax:

$$
\begin{align*}
e ::= &\ \cdots\\
  |\ \ &\texttt{case}\ e\ \texttt{of}\ \texttt{nil}\ \texttt{=>}\ e\ |\ x::x\  \texttt{=>}\ e
\end{align*}
$$

Typing rule:

$$
\frac{\Gamma\vdash e_1:t_1\ \texttt{list}\quad\Gamma\vdash e_2:t_2\quad\Gamma[h:t_1][t:t_1\ \texttt{list}]\vdash e_3:t_2}
{\Gamma\vdash\texttt{case}\ e_1\ \texttt{of}\ \texttt{nil}\ \texttt{=>}\ e_2\ |\ h::t\  \texttt{=>}\ e_3:t_2}
$$

Evaluation rules:

$$
\frac{E,M,p;e_1\Downarrow M',p';\texttt{nil}\quad E,M',p';e_2\Downarrow M'',p'';v_2}
{E,M,p;\texttt{case}\ e_1\ \texttt{of}\ \texttt{nil}\ \texttt{=>}\ e_2\ |\ h::t\  \texttt{=>}\ e_3\Downarrow M'',p'';v_2}
$$

$$
\frac{E,M,p;e_1\Downarrow M',p';(\texttt{cons},v_1,v_2)\quad E[h\mapsto v_1][t\mapsto v_2],M',p';e_3\Downarrow M'',p'';v_3}
{E,M,p;\texttt{case}\ e_1\ \texttt{of}\ \texttt{nil}\ \texttt{=>}\ e_2\ |\ h::t\  \texttt{=>}\ e_3\Downarrow M'',p'';v_3}
$$

Example:

```ocaml
(* new.fold.spl *)
let foldl = rec foldl =>
  fn f => fn x => fn l =>
    case l of
      nil    => x
    | h :: t => foldl f (f x h) t
in
  foldl (fn x => fn y => x + y) 0 (1::2::3::4::nil)
end
(* : int
   ==> 10 *)
```



#### Mutual recursion

Mutual recursion in ML dialects usually have two forms. The first form is mutual recursion bindings, like `let rec ... and ... ` in OCaml. The second form is in the original Y combinator style where a recursion is an expression rather than a binding. I see that SimPL prefers the latter so I implement mutual recursion in that form.

For syntax, I refer to [similar terms in Coq](https://coq.inria.fr/distrib/current/refman/language/core/inductive.html#recursive-functions-fix) like `fix <ident> := ... with <ident> :=  ... for <ident>`,  where multiple values are defined and one of them is returned. The other values should be considered and designed as helpers since they can not be accessed outside, which is also a limitation of mutual recursion in the second form.

The original recursion syntax is modified to:

$$
\begin{align*}
e ::= &\ \cdots\\
  |\ \ &\texttt{rec}\ r\\
r ::=&\ r\ \texttt{with}\ x\ \texttt{=>}\ e\\
  |\ \ &x\ \texttt{=>}\ e
\end{align*}
$$

I sacrifices the `for <ident>` part for a uniform syntax and make the parser generator happy. The first value is returned. But in the following part I will use $\texttt{rec}\ r_n\ \texttt{for}\ x_k$ syntax for convenience, where $r_n$ represents $x_1\ \texttt{=>}\ e_1\ \texttt{with}\ x_2\ \texttt{=>}\ e_2\cdots\texttt{with}\ x_n\ \texttt{=>}\ e_n$.

The typing rule is modified to:

$$
\frac{\forall i,\Gamma[x_i:t_i]\vdash e_i:t_i}
{\Gamma\vdash\texttt{rec}\ r_n\ \texttt{for}\ x_k:t_k}
$$

Value definition:

$$
\bf{Rec}=\{\texttt{rec}\}\times\bf{Env}\times\bf{Var}\times(\bf{Var}\rightarrow\bf{Exp})
$$

Here $\bf{Var}\rightarrow\bf{Exp}$ means an mapping from $\bf{Var}$ to $\bf{Exp}$.

Evaluation rules:

$$
\frac{E[x_i\mapsto(\texttt{rec},E,x_i,\{x_j\mapsto e_j\}^*)]^*,M,p;e_k\Downarrow M',p';v}
{E,M,p;\texttt{rec}\ r_n\ \texttt{for}\ x_k\Downarrow M',p';v}
$$

$$
\frac{E(x)=(\texttt{rec},E_1,x_1,\{x_i\mapsto e_i\}^*)\quad E_1,M,p;\texttt{rec}\ r_n\ \texttt{for}\ x_1\Downarrow M',p';v}
{E,M,p;x\Downarrow M',p';v}
$$

Example:

```ocaml
(* new.mutualrec.spl *)
let iseven =
  rec e => fn x => if iszero x then true else o (x-1)
  with o => fn x => if iszero (pred x) then true else e (x-1)
in
  iseven 6
end
(* : bool
   ==> true *)
```



#### Garbage collection of reference cells

I implement a classical mark-and-sweep garbage collector for reference cells.

```java
public int allocate(Env e, Value v) {
    if (mem.size() >= gcThreshold) {
        gc(e);
    		gcThreshold = 2 * mem.size();
  	}
    int p = pointerGen;
    pointerGen += 1;
    mem.put(p, v);
    return p;
}
```

Each time the interpreter allocates memory cell for value, if the current active cells exceeds the threshold then the garbage collector will scan the environment and collect the unreachable cells. The next stage threshold will be the double of number of active cells after garbage collection.



#### Lazy evaluation

Make arbitrary part of the expression can be lazily evaluated is a little complex. Implementing it in a naive way have some disadvantages:

- Explicit forces across the interpreter, which will result in a verbose code.
- Performance penalty. Forced thunks is hard to be shared when arbitrary part of the expression can be lazily evaluated, resulting in multiple evaluations of the same expression.

One way to solve the above problems is using graph reduction technique. But it requires a complete refactor of the interpreter architecture. For simplicity without a great loss of performance and functionality,  lazy bindings and functions evaluating arguments lazily (which is actually a lazy binding) can be introduced.

The syntax:

$$
\begin{align*}
e ::= &\ \cdots\\
  |\ \ &\texttt{lazy}\ x=e\ \texttt{in}\ e\ \texttt{end}\\
  |\ \ &\texttt{lazyfn}\ x\ \texttt{=>}\ e
\end{align*}
$$

The typing rules are similar to those of $\texttt{let}$ and $\texttt{fn}$ expressions.

Value definition:

$$
\begin{align*}
V_{thunk}&=\{\texttt{thunk}\}\times\bf{Env}\times\bf{Exp}\\
V_{t_1\rightarrow t_2}&=\{\texttt{fun}\}\times\bf{Env}\times\bf{Var}\times\bf{Exp}\times\mathbb{B}
\end{align*}
$$

Evaluation rules:

$$
\frac{E[x\mapsto (\texttt{thunk},E,e_1)],M,p;e_2\Downarrow M',p',v}
{E,M,p;\texttt{lazy}\ x=e\ \texttt{in}\ e\ \texttt{end}\Downarrow M',p';v}
$$

$$
\frac{}{E,M,p;\texttt{fn}\ x\ \texttt{=>}\ e\Downarrow M,p;(\texttt{fun},E,x,e,\bf{ff})}
\qquad
\frac{}{E,M,p;\texttt{lazyfn}\ x\ \texttt{=>}\ e\Downarrow M,p;(\texttt{fun},E,x,e,\bf{tt})}
$$

$$
\frac{E,M,p;e_1\Downarrow M',p';(\texttt{fun},E_1,x,e,\bf{ff})\quad E,M',p';e_2\Downarrow M'',p'';v_2\quad E_1[x\mapsto v_2],M'',p'';e\Downarrow M''',p''';v}
{E,M,p;e_1\ e_2\Downarrow M''',p''';v}
$$

$$
\frac{E,M,p;e_1\Downarrow M',p';(\texttt{fun},E_1,x,e,\bf{tt})\quad E_1[x\mapsto (\texttt{thunk},E,e_2)],M',p';e\Downarrow M'',p'';v}
{E,M,p;e_1\ e_2\Downarrow M',p';v}
$$

$$
\frac{E(x)=(\texttt{thunk},E_1,e)\quad E_1,M,p;e\Downarrow M',p';v\quad E\{x\mapsto v\}}
{E,M,p;x\Downarrow M,p;v}
$$

In the last rule $E\{x\mapsto v\}$ means a local update of the environment, it guarantees that lazy bindings are evaluated at most once. It is not correct to write it in this form while theoretically the judgement form and all rules should be changed where $E$ can gets updated sequentially like $M$ and $p$.

In real implementation, while the environments originally behaves like immutable linked lists, it is actually a persistent data structure where two environments share the most common parts of name-value mapping, which enables me to implement local environment updating easily without changing previous code.

Example1: the lazy binding will not be evaluated if not accessed.

```ocaml
(* new.lazy1.spl *)
let const = fn x => lazyfn y => x in
  lazy a = hd nil in
    const () (hd nil)
  end
end
(* : unit
   ==> unit *)
```

Example2: the lazy binding will be evaluated at most once.

```ocaml
(* new.lazy2.spl *)
let x = ref 0 in
  lazy y = (x := !x + 1) in
    y;y;!x
  end
end
(* : int
   ==> 1 *)
```



### Demos

Sieve of Eratosthenes:

```ocaml
(* new.primes.spl *)
let filter = rec filter =>
  fn f => fn l =>
    case l of
      nil    => nil
    | h :: t =>
        let rest = filter f t in
        if (f h) then (h :: rest) else rest
        end
in
let fromTo = rec fromTo =>
  fn m => fn n =>
    if (m > n) then nil else (m :: (fromTo (succ m) n))
in
let sieve = rec sieve =>
  fn l =>
    case l of
      nil       => nil
    | p :: rest =>
        p :: (sieve (filter (fn n => n % p <> 0) rest))
in
let primes = fn n =>
  sieve (fromTo 2 n)
in
  primes 1000
end end end end
(* : int list
   ==> list@168 *)
(* prime list in [2,1000] *)
```

N-queens:

```ocaml
(* new.8queen.spl *)
let foldr = rec foldr =>
  fn f => fn x => fn l =>
    case l of
      nil    => x
    | h :: t => f h (foldr f x t)
in
let map = rec map =>
  fn f => fn l =>
    case l of
      nil    => nil
    | h :: t => (f h) :: (map f t)
in
let filter = rec filter =>
  fn f => fn l =>
    case l of
      nil    => nil
    | h :: t =>
        let rest = filter f t in
        if (f h) then (h :: rest) else rest
        end
in
let append = rec append =>
  fn l1 => fn l2 =>
    case l1 of
      nil    => l2
    | h :: t => h :: (append t l2)
in
let concat = foldr append nil in
let concatMap = fn f => fn l => concat (map f l) in
let fromTo = rec fromTo =>
  fn m => fn n =>
    if (m > n) then nil else (m :: (fromTo (succ m) n))
in
let queens = fn n =>
  let loop =
    rec loop => fn boards => fn counter =>
      if (counter = n)
      then boards
      else (loop (concatMap expand boards) (succ counter))
    with expand => fn board =>
      map (fn x => x :: board) (filter (fn x => safe x board 1) (fromTo 1 n))
    with safe => fn x => fn l => fn n =>
      case l of
        nil    => true
      | c :: y => (x <> c) andalso (x <> (c + n)) andalso (x <> (c - n))
                           andalso (safe x y (succ n))
  in
    loop (nil::nil) 0
  end
in
  queens 8
end end end end end end end end
(* : int list list
   ==> list@92 *)
(* all solutions for 8-queen problem
   92 solutions
   each is a int list of positions at each row
 *)
```

