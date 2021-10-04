# Problème de 8 reines
Le problème des N Reines est un échiquier où nous avons un nombre N de Reines positionnées sur l'échiquier et l'état cible à atteindre est de les avoir toutes positionnées de manière à ce qu'aucune des Reines ne soit dans une position permettant à une autre Reine de l'attaquer. En d'autres termes, nous pouvons considérer ce problème comme l'échiquier et les positions des N Reines à une étape sont l'état actuel et l'état cible est celui où toutes les N Reines sont positionnées de manière à ce qu'aucune attaque ne puisse se produire. 

Une reine peut attaquer une autre si elles sont toutes les deux sur :  

La même __ligne__, la même __colonne__, ou la même __diagonale__.

![This is an image](/src/HillClimbing/images.png)
# HillClimbing 
L'algorithme d’escalade est un algorithme de recherche locale qui se déplace de manière continue dans la direction d'une évaluation croissante pour trouver le pic de la montagne ou la meilleure solution au problème. Il s'arrête lorsqu'il atteint un pic où aucun voisin n'a une valeur plus élevée.
  ### 1. Caractéristiques de l'algorithme Hill climbing 
  - __No backtraking__ : Il ne fait pas de retour en arrière dans l'espace de recherche, car il ne se souvient pas des états précédents. 

  - __Générer et tester des alternatives__ : L'algorithme Hill Climbing est une variante de l'algorithme générer et tester. L’algorithme Générer et tester produit un feedback qui permet de décider de la direction à prendre dans l'espace de recherche. 

    L'algorithme de génération et de test est le suivant : 

      - Générer des solutions possibles. 

      - Testez pour vérifier si cela correspond à la solution souhaitée. 

      - Si la solution a été trouvée quittez sinon retournez à la 1er étape. 

  - __Greedy approach__ : En tout point de l'espace d'état, la recherche se dirige uniquement dans la direction qui optimise le coût de la fonction avec l'espoir de trouver la solution optimale à la fin.
  ### 2. Diagramme de l'espace d'état
  ![This is an image](https://miro.medium.com/max/672/1*ymMUv1hzqBYPL-fErH7vIQ.png)
  
  Le diagramme de l'espace d'état est une représentation de l'algorithme d'escalade qui montre les différents états de l'algorithme et les valeurs des fonctions de coût correspondantes. 
  Les différentes régions dans le diagramme de l'espace d'état : 
   * __Le maximum global__ (Global maximum) : est le meilleur état possible du paysage de l'espace d'état. Il a la valeur la plus élevée de la fonction objectif. 

   * __Maximum local__ (Local maximum) : C'est un état qui est meilleur que l'état voisin, mais il existe un état qui est meilleur que lui (maximum global). Cet état est meilleur parce qu'ici la valeur de la fonction objectif est plus élevée que ses voisins. 

   * __Maximum Local__ Plat (Plateua) : Cette région est représentée par une ligne droite où tous les états voisins ont la même valeur, de sorte que chaque nœud est un maximum local dans la région. 

   * __Epaule__ (Shoulder) : Il s'agit d'une région de plateau qui présente un bord ascendant. Elle s'incurve un peu plus loin et conduit à un état plus élevé. 

   * __État actuel__ (Current State) : c'est un état dans un diagramme de paysage où un agent est actuellement présent.
   ## Résultat obtenu
   ### Etat Initial
   ![This is an image](/src/HillClimbing/etatinitial.PNG)
         
   ### Etat Final    
  ![This is an image](/src/HillClimbing/etatfinal.PNG)
