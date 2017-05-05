[![CircleCI](https://circleci.com/gh/uy-rrodriguez/ceri-m1-test/tree/master.svg?style=svg)](https://circleci.com/gh/uy-rrodriguez/ceri-m1-test/tree/master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/773a56215a134e61a9e2da66ddfd7165)](https://www.codacy.com/app/uy-rrodriguez/ceri-m1-test?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=uy-rrodriguez/ceri-m1-test&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/773a56215a134e61a9e2da66ddfd7165)](https://www.codacy.com/app/uy-rrodriguez/ceri-m1-test?utm_source=github.com&utm_medium=referral&utm_content=uy-rrodriguez/ceri-m1-test&utm_campaign=Badge_Coverage)


# UCE Génie Logiciel Avancé : Techniques de tests

### Trainer : RODRIGUEZ, Ricardo
### Team : Alternants
#### CERI M1 S1 ILSEN, Université d'Avignon et des Pays de Vaucluse


## Introduction
Ce projet représente une implémentation simplifiée d'un jeu du style de PokemonGO afin d'apprendre des techniques d'Intégration Continue.

Nous utilisons les outils Maven, CircleCI, Codacy et Jacaco, pour simplifier le codage, tests et évaluation de la qualité du code.


## Détails d'implémentation
Une note personnelle dirigé au professeur, concernant l'implémentation demandée :

Je considère que la compléxité de l'implémentation exigée est excesive pour un cours de test. Pendant les cours magistraux nous n'avons jamais vu les technologies à utiliser pour réaliser l'implémentation. En plus, entre tous les autres projets à faire et le temps passé en entreprise, il a été très difficile d'investir le temps nécessaire pour rendre un code fonctionnel.

En ce qui me concerne, j'ai réussi à mettre en ligne un service, basé sur du code trouvé sur Internet, qui m'aurait permis de récupérer les métadonnées des Pokemons et réaliser le calcul des IVs, sans passer par l'analyse des sites web qui le font et l'utilisation de Selenium. À nouveau, toutes les technologies à utiliser nécessaires pour l'implémentation n'ont pas été vues en cours, il aurait donc fallu investir une quantité de temps que l'on n'a pas pour les prendre en main, les appliquer au code Java, etc. J'ai quand même essayé de parser le résultat des pages avec Selenium pour Java, sans succès.

Le service qui retourne les métadonnées :

https://poke-metadata.herokuapp.com/ivcal.php?method=getIVs&name=Bulbizarre&cp=613&hp=64&dust=4000&powerup=yes

https://poke-metadata.herokuapp.com/ivcal.php?method=getMetadata&name=Bulbizarre


Malgré ma motivation initiale en ce qui concernais le sujet du TP et mes efforts pour arriver à faire toute l'implémentation, je rends un code qui n'est pas fonctionnel, adapté simplement afin que les tests passent.
