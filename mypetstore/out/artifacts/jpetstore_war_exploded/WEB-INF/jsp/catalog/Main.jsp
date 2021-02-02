<%@ include file="../common/IncludeTop.jsp"%>

    <div id="Welcome">
        <div id="WelcomeContent">
            Welcome to MyPetStore!
        </div>
    </div>

    <div id="inform" class="inform">sss</div>

    <div id="Main">
        <div id="Sidebar">
            <div id="SidebarContent">
                <a href="viewCategory?categoryId=FISH" ><img src="images/fish_icon.gif" /></a>
                <br/> Saltwater, Freshwater <br/>
                <a href="viewCategory?categoryId=DOGS" ><img src="images/dogs_icon.gif" /></a>
                <br /> Various Breeds <br />
                <a href="viewCategory?categoryId=CATS" ><img src="images/cats_icon.gif" /></a>
                <br /> Various Breeds, Exotic Varieties <br />
                <a href="viewCategory?categoryId=REPTILES" ><img src="images/reptiles_icon.gif" /></a>
                <br /> Lizards, Turtles, Snakes <br />
                <a href="viewCategory?categoryId=BIRDS" ><img src="images/birds_icon.gif" /></a>
                <br /> Exotic Varieties
            </div>
        </div>



        <div id="MainImage">
            <div id="MainImageContent">
                <map name="estoremap" id="estoremap">
                    <area alt="Birds" coords="72,2,280,250" href="viewCategory?categoryId=BIRDS" shape="rect" />
                    <area alt="Fish" coords="2,180,72,250" href="viewCategory?categoryId=FISH" shape="rect"  id="FISH"/>
                    <area alt="Dogs" coords="60,250,130,320" href="viewCategory?categoryId=DOGS" shape="rect" id="DOGS"/>
                    <area alt="Reptiles" coords="140,270,210,340" href="viewCategory?categoryId=REPTILES" shape="rect" id="REPTILES"/>
                    <area alt="Cats" coords="225,240,295,310" href="viewCategory?categoryId=CATS" shape="rect" id="CATS"/>
                    <area alt="Birds" coords="280,180,350,250" href="viewCategory?categoryId=BIRDS" shape="rect" id="BIRDS"/>
                </map>
                <img height="355" src="images/splash.gif" align="middle" usemap="#estoremap" width="350" />
            </div>
        </div>
        <div id="Separator">&nbsp;</div>
    </div>
    <script src="js/mouse-hover.js"></script>
    <%@include file="../common/IncludeBottom.jsp"%>
</div>

</body>
</html>