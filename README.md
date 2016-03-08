Quentin Baert  
Master MOCAD

# TSP

## Compilation

Le projet a été codé à l'aide de Scala et de SBT (Scala Build Tool, disponible ici : http://www.scala-sbt.org/download.html). Ces deux outils sont nécessaire à la compilation.

Pour compiler, exécuter le script `compile.sh` de la manière suivante :
```
./compile.sh
```

Le jar exécutable est alors crée à la racine du projet.

## Exécution

Pour exécuter, utiliser la commande suivante :
```
java -jar approx.jar -sc vector -instance [AB|CD|EF] -ng [nombre de vecteurs de poids]
```
ou
```
java -jar approx.jar -sc pareto -instance [AB|CD|EF] -ng [nombre de générations] -nn [nombre de voisins par solution]
```

L'ordre des options n'est pas important.

Chaque exécution produit un script gnuplot qui permet de visualiser le résultat.
```
gnuplot makeApproxPlot.gnuplot
open pareto.png
```

Pour supprimer les fichiers liés à un run :
```
./clean.sh
```
