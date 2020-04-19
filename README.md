Projet de compilation 
=========

## Composition du groupe

Le groupe est composé de **Quentin GARRIDO** et **Félix Marcoccia**

## Faire fonctionner le projet

### Compiler le projet

Pour lancer le projet, il suffit d'effectuer la commande `make build`.

### Lancer les tests

Pour lancer tous les tests (positifs comme négatifs) il suffit de lancer la commande `make check`.
Pour chacun des programmes de test, la sortie sera comme dans l'exemple suivant :
```
=================================
java Jagger ./tests/pos\_aff.txt
LET
NEW VAR a:= 2.0
IN
PRINT((VAR a))
((VAR a) := 3.0)
PRINT((VAR a))
END

2.0
3.0
=================================
```
Nous avons en premier le nom du fichier, puis le pretty printer et enfin l'évaluation, ou le cas échéant les erreurs rencontrées.
### Tester un fichier en particulier

Pour essayer un code en particulier, il faut utiliser la commande `make run file=FICHIER` avec FICHIER le nom du fichier contenant
le code à éxécuter.

## Résumé du travail réalisé

|Tâche| Fait |
|---|---|
| **tarball** | :heavy\_check\_mark: |
| **make marche** | :heavy\_check\_mark: |
| **make check** | :heavy\_check\_mark: |
| **entiers avec bonnes priorités** | :heavy\_check\_mark: |
| **primitive print** | :heavy\_check\_mark: |
| **support de if-then-else** | :heavy\_check\_mark: |
| **support de if-then-else** | :heavy\_check\_mark: |
| **support des variables et des scopes** | :heavy\_check\_mark: |
| **affectation** | :heavy\_check\_mark: |
| **support de while** | :heavy\_check\_mark: |
| **support de for** | :heavy\_check\_mark: |

## Détail du travail réalisé

### Grammaire du langage

| Nom | Définition | Abbréviation |
|---|---|---|
|mainloop | M -> S \<EOF\> | M |
|scope | S -> \<LET\> (D)\* \<IN\> St (,St)\* \<END\> | S |
|declaration | D -> \<VAR\> \<ID\> \<ASSIGN\> (Cond\|Cmp)| D |
|for | F -> \<FOR\> \<ID\> \<ASSIGN\> (Cond\|Cmp)\<TO\> (Cond\|Cmp)\<DO\> S(,S)\*| F |
|while | W -> \<WHILE\> Cmp \<DO\> S(,S)\* | W |
|assignment | A-> \<ID\> \<ASSIGN\> (Cond\|Cmp) | A |
|statement | St -> P \| Cond \| CMP \| W \| F \| S | St |
|conditional | Cond -> \<IF\> (Cond\|Cmp) \<THEN\> St \<ELSE\> St| Cond |
|print | \<PRINT\> '(' Cond\|Cmp ')' | P |
|comparison | Cmp -> E ('<'E \| '<='E\| '>'E \| '>='E \| '<>'E \| '='E)?| Cmp |
|expression |E -> T ('+'E \| '-'E)? | E |
|term |T -> F ('\*'F \| '/'F)\* | T |
|factor | F ->\<ID\> \| \<NUMBER\> \| \<STRING\> \| '(' St ')' \| '+' F \| '-' F | F |

### Liste des Tokens

| Token | Définition |
|---|---|
|NUMBER | (\<DIGIT\>)+ ('.' (\<DIGIT\>)\*)? |
|DIGIT | ['0'-'9'] |
|STRING | "\"" (~["\""])\* "\""|
|PRINT | 'print' |
|IF | 'if' |
|THEN |'then' |
|ELSE |'else' |
|LET |'let' |
|IN |'in' |
|END |'end' |
|ASSIGN |':=' |
|VAR |'var' |
|WHILE |'while' |
|FOR |'for' |
|DO |'do' |
|TO |'to' |
|ID |['a'-'z' , 'A'-'Z'] (['a'-'z','A'-'Z','0'-'9'])\* |

## Originalités du rendu

### Évaluation en plusieurs étapes

Dans ce projet, nous avons réalisé l'évaluation (interpréteur) en plusieurs étapes comme suit :

**Binder** -> **Renamer** -> **Typechecker** -> **Évaluateur**

Le but était de garder un typechecker statique et d'avoir une simple hashmap à l'évaluation pour les
varaibles.

Le Binder ici va créer notre threaded AST nécessaire au type checker statique. Ce threaded AST nous évite
de devoir garder la pile de hashmap des scopes en dehors du binder, comme chaque variable est reliée à sa définition.

Pour comprendre le rôle du renamer, il faut comprendre le problème causé par l'évaluation des variables.
Comme nous n'avons pas de pile de hashmap de nos variables lors de l'évaluation, la première idée est d'évaluer
la déclaration des variables. Cependant cela pose problème si nous appelons plusieurs fois une variable comme suit:
```
let
  var foo := 2+3*(13000+4)*-2
in
  foo,foo,foo
end
```
Ici, nous allons calculer 3 fois `2+3*(13000+4)*-2` ce qui n'est pas nécessaire car nous n'avons pas d'effets de bords qui
pourraient modifier la valeur de retour de cette expression.

la solution est donc de stocker l'évaluation de chaque variable, et si une variable n'a jamais été évaluée nous allons l'évaluer
et stocker le résultat dans une hashmap qui associe une variable à son évaluation.
Cependant dans le cas suivant :
```
let
  var foo :=1
in
  let
	var foo :=2
	var bar := "a"
  in
	foo,
  end,
  foo
end
```
Nous avons deux variables foo dans plusieurs scopes, il faut donc les différencier.
Pour cela le renamer va rajouter à la fin de chaque nom de variable un numéro différent, ce qui nous donne alors :

```
let
  var foo_1 :=1
in
  let
	var foo_2 :=2
	var bar_3 := "a"
  in
	foo_2,
  end,
  foo_1
end
```
Ici le problème est résolu. En pratique nous allons juste changer le nom de la déclaration mais pas celui des variables
en elle mêmes, ainsi appeler *foo* dans le second scope fera référence à *foo\_2* de manière transparente et assez simple.

Grâce à tout cela, nous avons pu maintenir chaque étape séparée ce qui réduit la complexité globale de l'interpréteur.
Chaque partie toute seule est relativement simple, mais tout faire ensemble aurait été bien plus complexe.

### Désucrage

Pour implémenter les unaires et la boucle for nous avons choisi de procéder au désucrage en binop et while respectivement.
Dans le cas du unaire `-2` devient alors `(0-2)`par exemple.

Pour la boucle for, la boucle suivante :
```
for i:= start to end do X
```
Devient alors :
```
let
  var i := start
in
  while i < end do (X, i := i+1)
end
```
Cela nous évite de devoir faire un gestion différente pour ces cas de figure et simplifie toutes les étapes
suivantes, en réduisant le nombre de composants différents à gérer.

### Détéction de toutes les erreurs

Lorsque lors d'une des étapes de l'interpréteur (notamment le binder et le typechecker) une erreur est rencontrée,
nous allons continuer à parcourir le programme et à analyser le reste. Le point positif est que nous détéctons aussi
les erreurs dans le reste du programme, mais le point négatif est que souvent une erreur en crée d'autres, et il faut uniquement
regarder la première erreur pour juger.

Malgré cela nous trouvions que c'est une fonctionnalité intéressante et avons choisi de l'implémenter.
Cependant si nous rencontrons une erreur à n'importe quelle étape, nous n'effectuons pas les étapes suivantes car
elles partent du principe que tout s'est bien passé avant,

## Difficultés rencontrées

Aucune difficulté majeure n'a été rencontrée durant le projet.

## Pistes d'améliorations

### Parseur

Bien que notre parseur fonctionne, notre inexpérience avec JavaCC a fait que notre code pourrait être bien plus
propre, notamment si nous avions élaboré la grammaire en entier dès le début.
Malheureusement nous n'avons pas eu assez de temps pour refaire le parser de la manière la plus propre possible.

### Détéction des erreurs

Certaines erreurs ne sont pas détectées à l'endroit le plus clair. Par exemple dans le code suivant :
```
let
  var foo := 1
  var foo := 2
in
  1
end
```
L'erreur de double déclaration de *foo* est détecté par le parseur, lors de l'ajout de la déclaration dans le scope.
Selon nous cela aurait fait plus de sens de détecter cela dans le binder, mais cela aurait complexifié le code pour
quelquechose d'anecdotique au final.
