# PicPay - Desafio Android
App para carregar uma lista de contatos

(<img src="https://github.com/mobilepicpay/desafio-android/blob/master/desafio-picpay.gif" width="300"/>)

## Tech stack
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- Koin for dependency injection.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Navigation Component - handle everything needed for in-app navigation.
  - Data Binding - declaratively bind observable data to UI elements.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Coil](https://github.com/coil-kt/coil) - loading images.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.


[comment]: <> (<img src="https://github.com/mobilepicpay/desafio-android/blob/master/desafio-picpay.gif" width="300"/>)

[comment]: <> (Um dos desafios de qualquer time de desenvolvimento é lidar com código legado e no PicPay isso não é diferente. Um dos objetivos de trazer os melhores desenvolvedores do Brasil é atacar o problema. Para isso, essa etapa do processo consiste numa proposta de solução para o desafio abaixo e você pode escolher a melhor forma de resolvê-lo, de acordo com sua comodidade e disponibilidade de tempo:)

[comment]: <> (- Resolver o desafio previamente, e explicar sua abordagem no momento da entrevista.)

[comment]: <> (- Discutir as possibilidades de solução durante a entrevista, fazendo um pair programming &#40;bate-papo&#41; interativo com os nossos devs.)

[comment]: <> (Com o passar do tempo identificamos alguns problemas que impedem esse aplicativo de escalar e acarretam problemas de experiência do usuário. A partir disso elaboramos a seguinte lista de requisitos que devem ser cumpridos ao melhorar nossa arquitetura:)

[comment]: <> (- Em mudanças de configuração o aplicativo perde o estado da tela. Gostaríamos que o mesmo fosse mantido.)

[comment]: <> (- Nossos relatórios de crash têm mostrado alguns crashes relacionados a campos que não deveriam ser nulos sendo nulos e gerenciamento de lifecycle. Gostaríamos que fossem corrigidos.)

[comment]: <> (- Gostaríamos de cachear os dados retornados pelo servidor.)

[comment]: <> (- Haverá mudanças na lógica de negócios e gostaríamos que a arquitetura reaja bem a isso.)

[comment]: <> (- Haverá mudanças na lógica de apresentação. Gostaríamos que a arquitetura reaja bem a isso.)

[comment]: <> (- Com um grande número de desenvolvedores e uma quantidade grande de mudanças ocorrendo testes automatizados são essenciais.)

[comment]: <> (  - Gostaríamos de ter testes unitários testando nossa lógica de apresentação, negócios e dados independentemente, visto que tanto a escrita quanto execução dos mesmos são rápidas.)

[comment]: <> (  - Por outro lado, testes unitários rodam em um ambiente de execução diferenciado e são menos fiéis ao dia-a-dia de nossos usuários, então testes instrumentados também são importantes.)
