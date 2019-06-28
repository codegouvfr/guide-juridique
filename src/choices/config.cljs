;; Copyright (c) 2019 DINSIC, Bastien Guerry <bastien.guerry@data.gouv.fr>
;; SPDX-License-Identifier: EPL-2.0
;; License-Filename: LICENSES/EPL-2.0.txt
(ns choices.config)

;; Allow users to send you emails with the summary?
(def mail-to "opensource@data.gouv.fr")

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
  {:title    "Guide juridique logiciel libre"
   :logo     "/img/logo-etalab-370x250.png"
   :color    "is-primary"
   :subtitle "Guide pour la publication des codes sources de l'administration"})

;; Website footer
(def footer
  {:text    "Ce site a été réalisée par la mission [Etalab](https://www.etalab.gouv.fr/).  Son code source est disponible [ici](https://github.com/etalab/guide-juridique-logiciel-libre)."
   :contact "opensource@data.gouv.fr"})

(def score {})

(def tree
  [{:name       "0"
    :text       "Présentation du guide"
    :home-page  true
    :force-help true
    :no-summary true
    :help       "Guide juridique pour la publication de logiciels libres dans l'administration."
    :choices    [{:answer "Commencer"
                  :goto   "1"
                  :color  "is-success"}]}
   
   {:name       "1"
    :text       "Produisez-vous ou recevez-vous un logiciel, dans le cadre de l’exercice d’une mission de service public ?"
    :start-page true
    :help       "Un logiciel est un ensemble des programmes, procédés et règles, et éventuellement de la documentation, relatifs au fonctionnement d'un ensemble de traitement de données. Une mission de service publique relève d’une activité d’intérêt général menée sous le contrôle de l’administration avec des prérogatives de puissance publique."
    :choices    [{:answer  "Oui"
                  :summary "Vous produisez ou recevez un <a target=\"new\" href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000033218936&cidTexte=LEGITEXT000031366350&dateTexte=20161009\">document administratif</a> qui doit être communiqué pourvu qu’il ait un intérêt économique, social, sanitaire ou environnemental."
                  :goto    "2"
                  :color   "is-success"}
                 {:answer  "Non"
                  :summary "Vous ne produisez ou ne recevez pas de <a target=\"new\" href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000033218936&cidTexte=LEGITEXT000031366350&dateTexte=20161009\">document administratif</a>, vous n’avez pas à établir une licence de réutilisation de données publiques."
                  :color   "is-warning"
                  :goto    "fin-1"}]}

   {:name    "2"
    :text    "Votre logiciel est-il achevé ?"
    :help    "Le droit à communication ne s'applique qu'à des <a href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000031367700&cidTexte=LEGITEXT000031366350&dateTexte=20160101\" target=\"new\">documents achevés</a>.  Un logiciel est considéré comme achevé dès lors qu’il est utilisé en production. "
    :choices [{:answer  "Oui"
               :summary "Votre logiciel est achevé."
               :goto    "3"
               :color   "is-success"}
              {:answer  "Non"
               :summary "Votre logiciel n’est pas achevé, vous n'avez pas à le communiquer."
               :color   "is-warning"
               :goto    "fin-1"}]}

   {:name    "3"
    :text    "Existe-il un secret faisant obstacle à la communication du logiciel ?"
    :help    "L’existence d’un secret peut empêcher la publication d’un logiciel.
<br/><br/>
Cela peut être un <a href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000033265181&cidTexte=LEGITEXT000031366350&dateTexte=20170701\" target=\"new\">secret absolu</a> : certains logiciels, dans l’intérêt public, ne peuvent être rendus publics par vous, notamment pour des questions sécuritaires et de sûreté nationale.
<br/><br/>
Le secret peut aussi être <a href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do;jsessionid=9E840349D3C48307C89BEA8DBA9C48B2.tplgfr23s_2?idArticle=LEGIARTI000033218964&cidTexte=LEGITEXT000031366350&dateTexte=20170701\" target=\"new\">relatif</a>. Il ne vaut alors qu’à l’égard de certains administrés, puisqu’il s’agit de protéger leurs intérêts privés, il peut s’agir : (1) du secret de la vie privée, documents comportant une appréciation ou un jugement de valeur sur une personne physique, ou faisant apparaître un comportement ; (2) du secret en matière commerciale et industrielle."
    :choices [{:answer  "Non"
               :summary "Votre logiciel ne contient pas de secret légal, relatif ou absolu."
               :color   "is-success"
               :goto    "5"}
              {:answer  "Oui"
               :summary "Votre logiciel est grevé par un secret. S’il est absolu, ces informations ne peuvent être communiquées à quiconque. S’il est relatif,  ces informations ne peuvent qu’être communiquées aux personnes directement concernées."
               :goto    "4"
               :color   "is-warning"}]}

   {:name    "4"
    :text    "Est-il possible d’occulter les éléments relevant d’un secret par un traitement automatisé d’usage courant sans que cela ne dénature ni ne vide de son sens le document ?"
    :help    "La communication des documents administratifs peut exiger d'<a target=\"new\" href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do;jsessionid=9E840349D3C48307C89BEA8DBA9C48B2.tplgfr23s_2?idArticle=LEGIARTI000031367719&cidTexte=LEGITEXT000031366350&dateTexte=20170701\">occulter</a> les secrets qu'ils contienneent.  Un document est « dénaturé » ou « vidé » de son sens s’il ne contient plus de données ou si les données clé pour la compréhension du document sont enlevées."
    :choices [{:answer  "Oui"
               :summary "Vous devez occulter des éléments."
               :goto    "7"
               :color   "is-success"}
              {:answer  "Non"
               :summary "Vous ne pouvez pas occulter ces éléments, vous ne pouvez pas publier ce logiciel."
               :color   "is-warning"
               :goto    "fin-1"}]}

   {:name    "5"
    :text    "Votre logiciel est-il composé d’éléments protégés par des droits tiers, par exemple extraits du code source d’autres logiciels ?"
    :help    "La diffusion du logiciel au public peut être empêchée par <a target=\"new\" href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?cidTexte=LEGITEXT000031366350&idArticle=LEGIARTI000033219038\">la détention, par un tiers, de droits de propriété intellectuelle sur une partie de votre logiciel</a>. Ce dernier peut, par exemple, être construit à partir d’éléments d’autres logiciels qui peuvent être protégés par des licences.
<br/><br/>
Une licence est un contrat par lequel le concepteur du logiciel en autorise l'exploitation par un tiers sous certaines conditions. Elle peut être libre, l'auteur concède alors tout ou partie de ses droits, en laissant au minimum 4 droits considérés fondamentaux aux utilisateurs (usage, étude, modification et redistribution de l'œuvre). Une licence explicite l’existence de droits tiers, mais il peut en exister même lorsqu’il n’y a pas de licence. "
    :choices [{:answer  "Non"
               :summary "Il n'existe pas de droits tiers sur le code source de votre logiciel."
               :color   "is-success"
               :goto    "7"}
              {:answer  "Oui, des éléments publiés sous licence libre."
               :summary "Votre logiciel intègre du code source tiers publié sous licence libre."
               :goto    "8"
               :color   "is-warning"}
              {:answer  "Oui, des éléments qui ne sont pas publiés sous licence libre."
               :summary "Votre logiciel intègre du code source tiers qui n'est pas publié sous licence libre."
               :goto    "6"
               :color   "is-danger"}]}

   {:name "6"
    :text "Est-il possible de publier votre logiciel séparément des éléments relevant de droits tiers, sans que cela ne dénature ni ne vide de son sens le logiciel ?"
    :help "Un document est « dénaturé » ou « vidé » de son sens s’il ne contient plus de données ou si les données-clés pour sa compréhension ne sont pas disponibles."
    
    :choices [{:answer  "Oui"
               :summary ["Il est possible d'occulter les éléments relevant de droits tiers." "Vous devez occulter ces éléments."]
               :goto    "7"
               :color   "is-success"}
              {:answer  "Non"
               :summary "Vous ne pouvez pas occulter ces éléments relevant de droits tiers."
               :color   "is-warning"
               :goto    "fin-1"}]}

   {:name    "7"
    :text    "Êtes-vous autorisé à pratiquer une forme de redevance ?"
    :help    "Il est possible pour vous d’exiger une <a target=\"new\" href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000033219051&cidTexte=LEGITEXT000031366350&dateTexte=20161009\">redevance</a> pour la réutilisation des données publiques que vous avez l’obligation de communiquer, si vous êtes tenue, en tant qu’administration, de couvrir par des recettes propres une part substantielle des coûts liés à l'accomplissement de vos missions de service public."
    :choices [{:answer  "Non"
               :summary "Vous n'êtes pas autorisé à pratiquer une forme de redevance, la réutilisation du code source est gratuite."
               :color   "is-success"
               :goto    "9"}
              {:answer  "Oui"
               :summary "Vous êtes autorisé à pratiquer une forme de redevance.  L’établissement d’une licence est obligatoire, mais celle-ci est libre, elle doit simplement <a target=\"new\" href=\"https://www.legifrance.gouv.fr/affichCodeArticle.do?idArticle=LEGIARTI000033219073&cidTexte=LEGITEXT000031366350&dateTexte=20161009\">respecter les conditions édictées par le Code des relations entre le public et l’administration</a>."
               :goto    "fin-2"
               :color   "is-warning"}]}

   {:name    "8"
    :text    "Êtes-vous juridiquement contraint par les droits tiers des logiciels libres à un choix limité de licences ?"
    :help    "Si le code source de votre logiciel intègre des codes sources publiés sous licence libre, la licence de ses éléments peut limiter votre choix de licence.  Par exemple, si votre code source intègre du code source publié sous licence GNU GPL, vous devrez utiliser la licence GNU GPL ou une licence compatible pour votre propre code source."
    :choices [{:answer  "Non"
               :summary "Le choix de votre licence est libre."
               :color   "is-success"
               :goto    "9"}
              {:answer  "Oui"
               :summary "Le choix de la licence est limité à celles qui vous sont autorisées par les licences des logiciels intégrés dans le vôtre."
               :goto    "fin-3"
               :color   "is-warning"}]}

   {:name    "9"
    :text    "La libre réutilisation des données publiques pourrait-elle porter atteinte à l’intérêt général ?"
    :help    "Vous devez choisir parmi une <a target=\"new\" href=\"https://www.data.gouv.fr/fr/licences\">liste de licences</a> qui peuvent être utilisées par les administrations pour la réutilisation à titre gratuit de leurs informations publiques. Vous avez le choix entre des licences dites « permissives » qui offrent une libre réutilisation, et des licences avec obligation de réciprocité, qui conditionnent la réutilisation à certains critères dont le partage des améliorations."
    :choices [{:answer  "Non"
               :summary "La libre réutilisation du logiciel ne porte pas atteinte à l'intérêt général."
               :color   "is-success"
               :goto    "fin-5"}
              {:answer  "Oui"
               :summary ["La libre réutilisation du logiciel ne porterait pas atteinte à l'intérêt général." "Vous devez opter pour une licence avec obligation de réciprocité."]
               :goto    "fin-4"
               :color   "is-warning"}]}

   {:name "fin-1"
    :text "Vous n’avez pas à rendre public le code source du logiciel."
    :done true}
   {:name "fin-2"
    :text "Vous devez établir une licence pour votre logiciel, mais celle-ci ne devra pas nécessairement faire partie de la liste des licences fixées par décret."
    :done true}
   {:name "fin-3"
    :text "Vous devez publier le code source de votre logiciel ; vous êtes limités dans votre choix de licence par les éléments que vous avez incorporés dans le code source de votre logiciel."
    :done true}
   {:name "fin-4"
    :text "Vous devez publier votre logiciel et <a target=\"new\" href=\"https://www.data.gouv.fr/fr/licences\">opter pour une licence avec obligation de réciprocité</a>."
    :done true}]
  {:name "fin-5"
   :text "Vous devez publier votre logiciel et <a target href=\"https://www.data.gouv.fr/fr/licences\">opter pour une licence permissive</a>."
   :done true})
