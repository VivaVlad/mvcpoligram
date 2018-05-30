<div class="navigation">
    <nav class="navbar navbar-inverse navbar-static-top">
           <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                     <img alt="Brand"  src="../../static/images/logo.PNG">
                </a>
            </div>

               <ul class="nav navbar-nav">
                <li><a href="#">Про нас</a></li>
                <li><a href="#">Контакти</a></li>
                <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="/#/users">Користувачі</a></li>
                    <li><a href="/#/albums">Альбоми
                    </a></li>
                </sec:authorize>
                   <sec:authorize access="hasRole('USER')">
                   <li><a href="/#/ualbums">Альбоми
                   </a></li>
                   </sec:authorize>

               </ul>
               <ul class="nav navbar-nav navbar-right">
               <sec:authorize access="isAnonymous()">
                   <li><a href="/#/registration"><span class="glyphicon glyphicon-user"></span> Зареєструватись</a></li>
                   <li><a href="/#/login"><span class="glyphicon glyphicon-log-in"></span> Увійти</a></li>
               </sec:authorize>
                   <sec:authorize access="isAuthenticated()">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Меню<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                         <li role="separator" class="divider"></li>

                            <li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span>Вийти</a></li>

                    </ul>
                </li>
                   </sec:authorize>
               </ul>
        </div>
    </nav>
</div>