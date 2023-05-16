;; Copyright (c) 2019-2023 DINUM, Bastien Guerry <bastien.guerry@data.gouv.fr>
;; SPDX-License-Identifier: EPL-2.0
;; License-Filename: LICENSES/EPL-2.0.txt
(ns choices.config)

;; Allow users to send you emails with the summary?
(def mail-to "contact@code.gouv.fr")

;; Display help along with questions by default?
(def display-help true)

;; Default locale for UI strings
(def locale "fr-FR")

;; Customize UI strings
;; For example: (def ui-strings {:redo "Restart from scratch})
(def ui-strings
  {:mail-subject "[Guide logiciel libre] Demande d'aide ou d'informations"
   :mail-body    "Bonjour,
je viens d'utiliser votre guide pour l'ouverture des logiciels de l'administration. Voici les résultats que j'obtiens:
%s
Je souhaite plus d'informations.
..."})

;; Website header
(def header
  {:title    "Guide juridique logiciels libres"
   :logo     "/img/logo-etalab-370x250.png"
   :color    "has-background-white-ter"
   :subtitle "Guide juridique pour la publication des codes sources de l'administration"})

;; Website footer
(def footer
  {:text    "Ce site a été réalisée par la mission [Etalab](https://www.etalab.gouv.fr/).  Son code source est disponible [ici](https://github.com/etalab/guide-juridique-logiciel-libre)."
   :contact "contact@code.gouv.fr"})

(def score {})

(def display-score false)

(defn score-function [scores])

(def tree
  [{:name       "0"
    :text       "Présentation du guide"
    :home-page  true
    :force-help true
    :no-summary true
    :help       "Depuis 2005, la loi a affirmé un droit de réutilisation des informations publiques. Celle-ci concerne à la fois les données publiques (« open data ») et les logiciels (« libres »).  L'esprit de la réutilisation est d'être la plus libre possible, afin de favoriser les usages. Depuis décembre 2015, la réutilisation gratuite constitue la règle, pour les données comme pour les logiciels.<br/><br/>Ce guide s'adresse aux administrations publiques qui détiennent les droits de propriété intellectuelle leur permettant d'établir une licence logicielle : il permet de savoir le quoi et le comment de la publication, et propose de vous aider à choisir entre les deux grandes familles de licences libres."
    :choices    [{:answer "Commencer"
                  :goto   "1"
                  :color  "is-success"}]}
   
   {:name       "1"
    :text       "Produisez-vous ou recevez-vous un logiciel, dans le cadre de l'exercice d'une mission de service public ?"
    :start-page true
    :help       "Un logiciel est un ensemble des programmes, procédés et règles, et éventuellement de documentations, relatifs au fonctionnement d'un ensemble de traitement de données. Une mission de service public relève d'une activité d'intérêt général menée sous le contrôle de l'administration avec des prérogatives de puissance publique."
    :choices    [{:answer  "Oui"
                  :summary "Vous produisez ou recevez un <a target=\"new\" href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000033218936&cidTexte=LEGITEXT000031366350&dateTexte=20161009\">document administratif</a> qui doit être communiqué pourvu qu'il ait un intérêt économique, social, sanitaire ou environnemental."
                  :goto    "2"
                  :color   "is-success"}
                 {:answer  "Non"
                  :summary "Vous ne produisez ou ne recevez pas de <a target=\"new\" href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000033218936&cidTexte=LEGITEXT000031366350&dateTexte=20161009\">document administratif</a>, vous n'avez pas à établir une licence de réutilisation de données publiques."
                  :color   "is-warning"
                  :goto    "fin-1"}]}

   {:name    "2"
    :text    "Votre logiciel peut-il être considéré comme « achevé » ?"
    :help    "Le droit à communication ne s'applique qu'à des <a href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000031367700&cidTexte=LEGITEXT000031366350&dateTexte=20160101\" target=\"new\">documents achevés</a>.  Un logiciel peut être considéré comme « achevé » dès lors que, dans l'exercice d'une mission de service public, il est déployé en production, est utilisé par des agents publics ou implémente un premier périmètre de fonctionnalités.  Un logiciel n'a pas besoin d'être en version 1.0 ou de sortir des statuts « alpha » ou « bêta » pour être considéré comme achevé."
    :choices [{:answer  "Oui"
               :summary "Votre logiciel est utilisé en production."
               :goto    "3"
               :color   "is-success"}
              {:answer  "Non"
               :summary "Votre logiciel ne peut pas être considéré comme achevé, vous n'êtes pas tenu de le communiquer.  Toutefois, il est de bonne pratique de publier les codes sources de vos logiciels dès la première ligne de code source écrite."
               :color   "is-warning"
               :goto    "fin-1"}]}

   {:name    "3"
    :text    "Le code source contient-il des secrets faisant obstacle à la communication du logiciel ?"
    :help    "La présence de secrets dans un code source peut empêcher la publication du logiciel associé.
<br/><br/>
Il peut s'agir d'un <a href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000033265181&cidTexte=LEGITEXT000031366350&dateTexte=20170701\" target=\"new\">secret absolu</a> : certains logiciels, dans l'intérêt public, ne peuvent être rendus publics par vous, notamment pour des questions sécuritaires et de sûreté nationale.
<br/><br/>
Le secret peut aussi être <a href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do;jsessionid=9E840349D3C48307C89BEA8DBA9C48B2.tplgfr23s_2?idArticle=LEGIARTI000033218964&cidTexte=LEGITEXT000031366350&dateTexte=20170701\" target=\"new\">relatif</a>, comme des informations relatives à la vie privée d'une personne, des informations comportant une appréciation ou un jugement de valeur sur une personne physique ou des informations commerciales et industrielles risquant de porter préjudice à un acteur économique.  Dans ce cas, le code source du logiciel n'est communicable qu'aux personnes pour qui ces informations n'ont pas à rester secrètes."
    :choices [{:answer  "Non"
               :summary "Votre logiciel ne contient pas de secret légal, relatif ou absolu."
               :color   "is-success"
               :goto    "5"}
              {:answer  "Oui"
               :summary "Votre logiciel est grevé par un secret. S'il est absolu, ces informations ne peuvent être communiquées à quiconque. S'il est relatif,  ces informations ne peuvent qu'être communiquées aux personnes directement concernées."
               :goto    "4"
               :color   "is-warning"}]}

   {:name    "4"
    :text    "Est-il possible d'occulter les éléments relevant d'un secret par un traitement automatisé d'usage courant sans que cela ne dénature ni ne vide de son sens le document ?"
    :help    "La communication des documents administratifs peut exiger d'<a target=\"new\" href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do;jsessionid=9E840349D3C48307C89BEA8DBA9C48B2.tplgfr23s_2?idArticle=LEGIARTI000031367719&cidTexte=LEGITEXT000031366350&dateTexte=20170701\">occulter</a> les secrets qu'ils contienneent.  Un document est « dénaturé » ou « vidé » de son sens s'il ne contient plus de données ou si les données clé pour la compréhension du document sont enlevées."
    :choices [{:answer  "Oui"
               :summary "Vous devez occulter des éléments."
               :goto    "7"
               :color   "is-success"}
              {:answer  "Non"
               :summary "Vous ne pouvez pas occulter ces éléments, vous ne pouvez pas publier ce logiciel."
               :color   "is-warning"
               :goto    "fin-1"}]}

   {:name    "5"
    :text    "Votre logiciel est-il composé d'éléments protégés par des droits tiers, par exemple extraits du code source d'autres logiciels ?"
    :help    "La diffusion du logiciel au public peut être empêchée par <a target=\"new\" href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?cidTexte=LEGITEXT000031366350&idArticle=LEGIARTI000033219038\">la détention, par un tiers, de droits de propriété intellectuelle sur une partie de votre logiciel</a>. Ce dernier peut, par exemple, être construit à partir d'éléments d'autres logiciels qui peuvent être protégés par des licences.
<br/><br/>
Une licence est un contrat par lequel le concepteur du logiciel en autorise l'exploitation par un tiers sous certaines conditions. Elle peut être libre, l'auteur concède alors tout ou partie de ses droits, en laissant au minimum 4 droits considérés fondamentaux aux utilisateurs (usage, étude, modification et redistribution de l'œuvre). Une licence explicite l'existence de droits tiers, mais il peut en exister même lorsqu'il n'y a pas de licence. "
    :choices [{:answer  "Non"
               :summary "Il n'existe pas de droits tiers sur le code source de votre logiciel."
               :color   "is-success"
               :goto    "7"}
              {:answer  "Oui, des éléments publiés sous licence libre."
               :summary "Votre logiciel intègre du code source tiers publié sous licence libre."
               :goto    "7"
               :color   "is-warning"}
              {:answer  "Oui, des éléments qui ne sont pas publiés sous licence libre."
               :summary "Votre logiciel intègre du code source tiers qui n'est pas publié sous licence libre."
               :goto    "6"
               :color   "is-danger"}]}

   {:name    "6"
    :text    "Est-il possible de publier votre logiciel séparément des éléments relevant de droits tiers, sans que cela ne dénature ni ne vide de son sens le logiciel ?"
    :help    "Un document est « dénaturé » ou « vidé » de son sens s'il ne contient plus de données ou si les données-clés pour sa compréhension ne sont pas disponibles."
    :choices [{:answer  "Oui"
               :summary ["Il est possible d'occulter les éléments relevant de droits tiers." "Vous devez occulter ces éléments."]
               :goto    "7"
               :color   "is-success"}
              {:answer  "Non"
               :summary "Vous ne pouvez pas occulter ces éléments relevant de droits tiers."
               :color   "is-warning"
               :goto    "fin-1"}]}

   {:name    "7"
    :text    "Êtes-vous juridiquement contraint par les droits tiers des logiciels libres à un choix limité de licences ?"
    :help    "Si le code source de votre logiciel intègre des codes sources publiés sous licence libre, la licence de ses éléments peut limiter votre choix de licence.  Par exemple, si votre code source intègre du code source publié sous licence GNU GPL, vous devrez utiliser la licence GNU GPL ou une licence compatible pour votre propre code source."
    :choices [{:answer  "Non"
               :summary "Le choix de votre licence est libre."
               :color   "is-success"
               :goto    "8"}
              {:answer  "Oui"
               :summary "Le choix de la licence est limité à celles qui vous sont autorisées par les licences des logiciels intégrés dans le vôtre."
               :goto    "fin-3"
               :color   "is-warning"}]}

   {:name    "8"
    :text    "Des motifs d'intérêt général pourraient-ils limiter la libre réutilisation ?"
    :help    "Vous devez choisir parmi une <a target=\"new\" href=\"https://www.data.gouv.fr/fr/licences\">liste de licences</a> qui peuvent être utilisées par les administrations pour la réutilisation à titre gratuit de leurs informations publiques. Par défaut, vous devez opter pour une licence permissive.  Si toutefois vous souhaitiez apporter des restrictions à la réutilisation, vous pouvez utiliser des licences dites « à réciprocité », à condition que ces restrictions soient justifiées par des motifs d'intérêt général et s'appliquent de façon proportionnée (cf. l'article [L323-2](https://www.legifrance.gouv.fr/codes/article_lc/LEGIARTI000033219073/) du CRPA.)"
    :choices [{:answer  "Non"
               :summary "La libre réutilisation du logiciel peut se faire sans limite."
               :color   "is-success"
               :goto    "fin-5"}
              {:answer  "Oui"
               :summary ["La libre réutilisation du logiciel doit être limitée, Étant entendu que cette limitation doit être proportionnée et ne doit pas avoir pour objet ou pour effet de porter atteinte à la concurrence." "Vous devez opter pour une licence avec obligation de réciprocité."]
               :goto    "fin-4"
               :color   "is-warning"}]}

   {:name "fin-1"
    :text "Vous n'êtes pas soumis au régime des documents administratifs."
    :done true}
   {:name "fin-2"
    :text "Vous établirez une licence pour votre logiciel, mais celle-ci ne devra pas nécessairement faire partie de la liste des licences fixées par décret."
    :done true}
   {:name "fin-3"
    :text "Vous publierez le code source de votre logiciel ; vous êtes limités dans votre choix de licence par les éléments que vous avez incorporés dans le code source de votre logiciel."
    :done true}
   {:name "fin-4"
    :text "Vous publierez votre logiciel et <a target=\"new\" href=\"https://www.data.gouv.fr/fr/licences\">opterez pour une licence avec obligation de réciprocité</a>."
    :done true}
   {:name "fin-5"
    :text "Vous publierez votre logiciel et <a target=\"new\" href=\"https://www.data.gouv.fr/fr/licences\">opterez pour une licence permissive</a>."
    :done true}])
