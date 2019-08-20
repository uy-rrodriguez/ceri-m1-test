[![CircleCI](https://circleci.com/gh/uy-rrodriguez/ceri-m1-test/tree/master.svg?style=svg)](https://circleci.com/gh/uy-rrodriguez/ceri-m1-test/tree/master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8ab81a738e7149b09e3fee05277ec346)](https://www.codacy.com/app/uy-rrodriguez/ceri-m1-test?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=uy-rrodriguez/ceri-m1-test&amp;utm_campaign=Badge_Grade)


# UCE Génie Logiciel Avancé : Techniques de tests

### Trainer : RODRIGUEZ, Ricardo
### Team : Alternants
#### CERI M1 S1 ILSEN, Université d'Avignon et des Pays de Vaucluse


## Introduction
Ce projet représente une implémentation simplifiée d'un jeu du style de PokemonGO afin d'apprendre des techniques d'Intégration Continue.

Nous utilisons les outils Maven, CircleCI, Codacy et Jacaco, pour simplifier le codage, tests et évaluation de la qualité du code.


## Détails d'implémentation

### Une note concernant l'implémentation demandée

À mon avis, la complexité de l'implémentation exigée est excessive par rapport au programme du cours, prenant en compte qu'il s'agit principalement d'un cours de test. Pendant les cours magistraux nous n'avons jamais vu certaines technologies nécessaires pour réaliser l'implémentation (notamment celle nécessaires pour récupérer les méta-données des Pokémons sur Internet : Selenium, webservices ou autres). De plus, entre tous les autres projets à faire et le temps passé en entreprise, il a été difficile d'investir le temps nécessaire pour rendre un code fonctionnel. J'ai quand même essayé de parser le résultat des pages avec Selenium pour Java, sans succès.

Finalement, après plusieurs heures d'investigation pour trouver le meilleur outil qui me permette de récupérer ces données, j'ai réussi à mettre en ligne un service PHP, basé sur du code Javascript trouvé sur Internet, qui m'a permis de récupérer les données des Pokémons et de réaliser le calcul des IVs, sans passer par le parsing des sites web avec Selenium ou des technologies similaires. Il a donc fallu investir une quantité de temps supplémentaire pour prendre le code en main, réaliser les appels HTTP en Java, etc. 


### Service PHP avec les métadonnées

Le service qui retourne les métadonnées se trouve sur un serveur Heroku personnel :

https://poke-metadata.herokuapp.com/ivcal.php?method=getIVs&name=Bulbizarre&cp=613&hp=64&dust=4000&powerup=yes

https://poke-metadata.herokuapp.com/ivcal.php?method=getMetadata&name=Bulbizarre


### Concernant le rendu

Je trouve que les technologies apprises lors des TPs sont très intéressantes, j'apprécie notamment la possibilité d'apprendre à utiliser Maven. Je rends un code plutôt fonctionnel qui permet de créer un Pokémon, le stocker en mémoire et évaluer le bon traitement de l'information retournée par le site PHP. Le code est couvert à un 90 %, une grande partie du 10 % restant représente principalement du code utilitaire et le traitement d'erreurs "étranges", par exemple l'inexistance du fichier qui traduit les noms des Pokémons de l'anglais au français. Néanmoins, les tests rendus ne sont pas exhaustifs.
