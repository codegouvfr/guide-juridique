[![img](https://img.shields.io/badge/Licence-EPL%2C%20Licence%20Ouverte-orange.svg?style=flat-square)](https://git.sr.ht/~etalab/guide-juridique-logiciel-libre/tree/master/item/LICENSES)


# Présentation

Ce dépôt contient le code pour une application web présentant un guide
juridique pour la publication des logiciels du secteur public.

L'application est déployée pour
[guide-juridique-logiciel-libre.etalab.gouv.fr](https://guide-juridique-logiciel-libre.etalab.gouv.fr).

Ce dépôt de code source est dérivé de l'appliation
<https://git.sr.ht/bzg/choices>.


# Test

1.  Configurez l'application en modifiant `src/cljs/choices/config.cljs`
2.  Testez le format de `config/tree` avec `lein fig:test`
3.  Compilez avec `lein fig:min`
4.  Vos fichiers statiques sont dans `resources/public/`


# Contribution

Ce dépôt ne permet pas d'ouvrir des issues ou des *pull requests*.

Les contributions au code sont les bienvenues sous forme de questions
ou de patches à envoyer à [~etalab/logiciels-libres@lists.sr.ht](mailto:~etalab/logiciels-libres@lists.sr.ht).


# Licence

2019-2021 DINUM, Etalab.

Le code de ce dépôt est publié sous [licence EPL 2.0](LICENSES/LICENSE.EPL-2.0.md) et les données du
dépôt sous [licence Ouverte 2.0](LICENSES/LICENSE.Etalab-2.0.txt).

