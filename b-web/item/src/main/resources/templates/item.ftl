<!DOCTYPE html>
<html lang="en">
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商品详情</title>
    <base href="/"/>
    <!--TODO-->
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/zhonglin.js"></script>
    <script type="text/javascript">
        function add_food(obj){
            var id=$(obj).attr("id");
            alert(id);
            var url="http://localhost:9098/cart/add/"+id+"/1"
            $.ajax({
                crossDomain:true,   //使其能够跨域传递cookie
                xhrFields:{withCredentials:true},
                url:url,
            })
        }
    </script>
</head>

<body style="position:relative;">
<!--top 开始-->
<div class="top">
    <div class="top-con w1200">
        <p class="t-con-l f-l">您好，欢迎购物!!!!!</p>
        <ul class="t-con-r f-r">
            <li><a href="#">我 (个人中心)</a></li>
            <li><a href="http://localhost:9098/cart/query">我的购物车</a></li>
            <li><a href="#">我的订单</a></li>
            <li class="erweima">
                <a href="#">扫描二维码</a>
                <div class="ewm-tu">
                    <a href="#"><img src="../static/images/erweima-tu.jpg" /></a>
                </div>
            </li>
            <div style="clear:both;"></div>
        </ul>
        <div style="clear:both;"></div>
    </div>
</div>

<!--logo search 开始-->
<div>
    <div class="search f-r" style="margin-left: 100px">
        <ul class="sp">
            <li class="current" ss-search="sp"><a href="JavaScript:;">商品</a></li>
            <li ss-search="dp"><a href="JavaScript:;">店铺</a></li>
            <div style="clear:both;"></div>
        </ul>
        <div class="srh">
            <div class="ipt f-l">
                <input type="text" placeholder="搜索商品..." ss-search-show="sp" />
                <input type="text" placeholder="搜索店铺..." ss-search-show="dp" style="display:none;" />
            </div>
            <button class="f-r">搜 索</button>
            <div style="clear:both;"></div>
        </div>
        <ul class="sp2">
            <li><a href="#">绿豆</a></li>
            <li><a href="#">大米</a></li>
            <li><a href="#">驱蚊</a></li>
            <li><a href="#">洗面奶</a></li>
            <li><a href="#">格力空调</a></li>
            <li><a href="#">洗发护发</a></li>
            <li><a href="#">葡萄 </a></li>
            <li><a href="#">脉动</a></li>
            <li><a href="#">海鲜水产</a></li>
            <div style="clear:both;"></div>
        </ul>
    </div>

    <div style="clear:both;"></div>
</div>

<!--切换城市-->
<div class="switch-city w1200">
    <a href="#" class="dianji-qh">切换城市</a>
    <span class="dqm">重庆市</span>
    <div class="select-city">
        <div class="sl-city-top">
            <p class="f-l">切换城市</p>
            <a class="close-select-city f-r" href="#">
                <img src="../static/images/close-select-city.gif" />
            </a>
        </div>
        <div class="sl-city-con">
            <p>西北</p>
            <dl>
                <dt>重庆市</dt>
                <dd>
                    <a href="#">长寿区</a>
                    <a href="#">巴南区</a>
                    <a href="#">南岸区</a>
                    <a href="#">九龙坡区</a>
                    <a href="#">沙坪坝区</a>
                    <a href="#">北碚</a>
                    <a href="#">江北区</a>
                    <a href="#">渝北区</a>
                    <a href="#">大渡口区</a>
                    <a href="#">渝中区</a>
                    <a href="#">万州</a>
                    <a href="#">武隆</a>
                    <a href="#">晏家</a>
                    <a href="#">长寿湖</a>
                    <a href="#">云集</a>
                    <a href="#">华中</a>
                    <a href="#">林封</a>
                    <a href="#">双龙</a>
                    <a href="#">石回</a>
                    <a href="#">龙凤呈祥</a>
                    <a href="#">朝天门</a>
                    <a href="#">中华</a>
                    <a href="#">玉溪</a>
                    <a href="#">云烟</a>
                    <a href="#">红塔山</a>
                    <a href="#">真龙</a>
                    <a href="#">天子</a>
                    <a href="#">娇子</a>
                </dd>

                <div style="clear:both;"></div>
            </dl>
            <dl>
                <dt>新疆</dt>
                <dd>
                    <a href="#">齐乌鲁木</a>
                    <a href="#">昌吉</a>
                    <a href="#">巴音</a>
                    <a href="#">郭楞</a>
                    <a href="#">伊犁</a>
                    <a href="#">阿克苏</a>
                    <a href="#">喀什</a>
                    <a href="#">哈密</a>
                    <a href="#">克拉玛依</a>
                    <a href="#">博尔塔拉</a>
                    <a href="#">吐鲁番</a>
                    <a href="#">和田</a>
                    <a href="#">石河子</a>
                    <a href="#">克孜勒苏</a>
                    <a href="#">阿拉尔</a>
                    <a href="#">五家渠</a>
                    <a href="#">图木舒克</a>
                    <a href="#">库尔勒</a>
                </dd>
                <div style="clear:both;"></div>
            </dl>
            <dl>
                <dt>甘肃</dt>
                <dd>
                    <a href="#">兰州</a>
                    <a href="#">天水</a>
                    <a href="#">巴音</a>
                    <a href="#">白银</a>
                    <a href="#">庆阳</a>
                    <a href="#">平凉</a>
                    <a href="#">酒泉</a>
                    <a href="#">张掖</a>
                    <a href="#">武威</a>
                    <a href="#">定西</a>
                    <a href="#">金昌</a>
                    <a href="#">陇南</a>
                    <a href="#">临夏</a>
                    <a href="#">嘉峪关</a>
                    <a href="#">甘南</a>
                </dd>
                <div style="clear:both;"></div>
            </dl>
            <dl>
                <dt>宁夏</dt>
                <dd>
                    <a href="#">银川</a>
                    <a href="#">吴忠</a>
                    <a href="#">石嘴山</a>
                    <a href="#">中卫</a>
                    <a href="#">固原</a>
                </dd>
                <div style="clear:both;"></div>
            </dl>
            <dl>
                <dt>青海</dt>
                <dd>
                    <a href="#">西宁</a>
                    <a href="#">海西</a>
                    <a href="#">海北</a>
                    <a href="#">果洛</a>
                    <a href="#">海东</a>
                    <a href="#">黄南</a>
                    <a href="#">玉树</a>
                    <a href="#">海南</a>
                </dd>
                <div style="clear:both;"></div>
            </dl>
        </div>
    </div>
</div>

<!--nav 开始-->
<div style="border-bottom:2px solid #F09E0B;">
    <div class="nav w1200">
        <a href="JavaScript:;" class="sp-kj" kj="">
            商品分类快捷
        </a>
        <div class="kj-show2 none" kj-sh="">
            <div class="kj-info1" mg="shiping">
                <dl class="kj-dl1">
                    <dt><a href="#">食品/饮料/酒水</a></dt>
                    <dd>
                        零食/糖果/巧克力、饼干/糕点、生鲜<br />
                        酒水饮料/乳饮料、调味/速食<br />
                        粮油/干货、冲调制品
                    </dd>
                </dl>
                <div class="kj-if-show" mg2="shiping">
                    <dl>
                        <dt>零食/糖果/巧克力</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>零食/糖果/巧克力</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>零食/糖果/巧克力</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>零食/糖果/巧克力</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>零食/糖果/巧克力</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl style="border-bottom:none;">
                        <dt>零食/糖果/巧克力</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                </div>
            </div>
            <div class="kj-info1" mg="muying">
                <dl class="kj-dl1">
                    <dt><a href="#">母婴用品/玩具</a></dt>
                    <dd>
                        奶粉/辅食、尿裤/湿巾、玩具<br />
                        宝宝喂养/洗护清洁
                    </dd>
                </dl>
                <div class="kj-if-show" mg2="muying">
                    <dl>
                        <dt>母婴用品/玩具</dt>
                        <dd>
                            <a href="#">巧克力</a>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">巧克力</a>
                            <a href="#">巧克力</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>母婴用品/玩具</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">巧克力</a>
                            <a href="#">膨化食品</a>
                            <a href="#">口香糖</a>
                            <a href="#">巧克力</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>母婴用品/玩具</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">巧克力</a>
                            <a href="#">膨食品</a>
                            <a href="#">糖克力/口香糖</a>
                            <a href="#">肉品</a>
                            <a href="#">巧克力</a>
                            <a href="#">进口食品</a>
                            <a href="#">巧克力</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>母婴用品/玩具</dt>
                        <dd>
                            <a href="#">巧克力</a>
                            <a href="#">坚果线</a>
                            <a href="#">巧克力</a>
                            <a href="#">膨食品</a>
                            <a href="#">果冻/克口香糖</a>
                            <a href="#">巧克力</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">巧克力</a>
                            <a href="#">进食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>母婴用品/玩具</dt>
                        <dd>
                            <a href="#">迷线</a>
                            <a href="#">巧克力</a>
                            <a href="#">膨化品</a>
                            <a href="#">巧克力</a>
                            <a href="#">糖果力/口香糖</a>
                            <a href="#">巧克力</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl style="border-bottom:none;">
                        <dt>母婴用品/玩具</dt>
                        <dd>
                            <a href="#">迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果克力/口香糖</a>
                            <a href="#">肉类/熟品</a>
                            <a href="#">进食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                </div>
            </div>
            <div class="kj-info1" mg="chuju">
                <dl class="kj-dl1">
                    <dt><a href="#">厨具餐具/家用清洁/纸品</a></dt>
                    <dd>
                        纸品湿巾、衣物清洁护理、家庭清洁<br />
                        清洁、厨具、烹饪用具/厨房配件<br />
                        食物保鲜容器
                    </dd>
                </dl>
                <div class="kj-if-show" mg2="chuju">
                    <dl>
                        <dt>厨具餐具</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>厨具餐具</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>厨具餐具</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>厨具餐具</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>

                        <dt>厨具餐具</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl style="border-bottom:none;">
                        <dt>厨具餐具</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                </div>
            </div>
            <div class="kj-info1" mg="meizhuang">
                <dl class="kj-dl1">
                    <dt><a href="#">美妆洗护/个人护理洗发护发</a></dt>
                    <dd>
                        洗浴用品/身体护理、口腔护理、面部护肤<br />
                        女性护理、彩妆/美容工具、男士护理
                    </dd>
                </dl>
                <div class="kj-if-show" mg2="meizhuang">
                    <dl>
                        <dt>美妆洗护</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>美妆洗护</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>美妆洗护</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>美妆洗护</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>美妆洗护</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl style="border-bottom:none;">
                        <dt>美妆洗护</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                </div>
            </div>
            <div class="kj-info1" style="border-bottom:none;" mg="jiafang">
                <dl class="kj-dl1" style="padding:6px 10px;">
                    <dt><a href="#">家居/家纺</a></dt>
                    <dd>
                        浴室用品、餐具水具<br />
                        收纳/居家日用、针织纺品
                    </dd>
                </dl>
                <div class="kj-if-show" mg2="jiafang">
                    <dl>
                        <dt>家居/家纺</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>家居/家纺</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>家居/家纺</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>家居/家纺</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>家居/家纺</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>家居/家纺</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>家居/家纺</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>家居/家纺</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>家居/家纺</dt>

                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl>
                        <dt>家居/家纺</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                    <dl style="border-bottom:none;">
                        <dt>家居/家纺</dt>
                        <dd>
                            <a href="#">坚果迷线</a>
                            <a href="#">膨化食品</a>
                            <a href="#">糖果果冻/巧克力/口香糖</a>
                            <a href="#">肉类/熟食食品</a>
                            <a href="#">进口食品</a>
                        </dd>
                        <div style="clear:both;"></div>
                    </dl>
                </div>
            </div>
        </div>
        <ul>
            <li><a href="#">首页</a></li>
            <li><a href="#">美食</a></li>
            <li><a href="#">外卖</a></li>
            <li><a href="#">休闲娱乐</a></li>
            <li><a href="#">酒店</a></li>
            <div style="clear:both;"></div>
        </ul>
        <div style="clear:both;"></div>
    </div>
</div>

<!--内容开始-->
<div class="details w1200">
    <div class="deta-info1">
        <div class="dt-if1-m f-l" style="margin-left: 300px">
            <div class="dt-ifm-hd">
                <h3><a href="#">${product.name!}</a></h3>
                <p>${product.salePoint!}</p>
            </div>
            <div class="dt-ifm-gojia">
                <dl>
                    <dt>宅购价</dt>
                    <dd>
                        <p class="p1">
                            <span class="sp1">¥${product.salePrice!}</span><span class="sp2">${product.price!}</span>
                        </p>
                        <p class="p2">
                            <span class="sp1"><img style="width: 200px" src="${product.image!}" />5分</span><span class="sp2">共有 2 条评价</span>
                        </p>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
            </div>
            <dl class="dt-ifm-spot">
                <dt>活动</dt>
                <dd>
                    <P><span>满赠</span>本店满500.00元赠300.00元礼品（随机赠送）</P>
                </dd>
                <div style="clear:both;"></div>
            </dl>
            <dl class="dt-ifm-box1">
                <dt>时间</dt>
                <dd>
                    <select>
                        <option>请选择配送日期</option>
                        <option>2015-8-31</option>
                        <option>2015-8-32</option>
                    </select>
                    <select>
                        <option>请选择配送时段</option>
                        <option>上午</option>
                        <option>下午</option>
                    </select>
                    <p>如果提前两天预定，还可以享受折扣哦！</p>
                </dd>
                <div style="clear:both;"></div>
            </dl>
            <dl class="dt-ifm-box3">
                <dt>数量</dt>
                <dd>
                    <a class="box3-left" href="JavaScript:;">-</a>
                    <input type="text" value="1">
                    <a class="box3-right" href="JavaScript:;">+</a>
                </dd>
                <div style="clear:both;"></div>
            </dl>
            <div class="dt-ifm-box4">
                <button class="btn1">进店选购</button>
                <button class="btn2" id="${product.id!}" onclick="add_food(this)">加入购物车</button>
                <button class="btn3">收藏</button>
            </div>
        </div>
        <div style="clear:both;"></div>
    </div>
</div>

<!--底部服务-->
<div class="ft-service">
    <div class="w1200">
        <div class="sv-con-l2 f-l">
            <dl>
                <dt><a href="#">新手上路</a></dt>
                <dd>
                    <a href="#">购物流程</a>
                    <a href="#">在线支付</a>
                </dd>
            </dl>
            <dl>
                <dt><a href="#">配送方式</a></dt>
                <dd>
                    <a href="#">货到付款区域</a>
                    <a href="#">配送费标准</a>
                </dd>
            </dl>
            <dl>
                <dt><a href="#">购物指南</a></dt>
                <dd>
                    <a href="#">常见问题</a>
                    <a href="#">订购流程</a>
                </dd>
            </dl>
            <dl>
                <dt><a href="#">售后服务</a></dt>
                <dd>
                    <a href="#">售后服务保障</a>
                    <a href="#">退款说明</a>
                    <a href="#">新手选购商品总则</a>
                </dd>
            </dl>
            <dl>
                <dt><a href="#">关于我们</a></dt>
                <dd>
                    <a href="#">投诉与建议</a>
                </dd>
            </dl>
            <div style="clear:both;"></div>
        </div>
        <div class="sv-con-r2 f-r">
            <p class="sv-r-tle">187-8660-5539</p>
            <p>周一至周五9:00-17:30</p>
            <p>（仅收市话费）</p>
            <a href="#" class="zxkf">24小时在线客服</a>
        </div>
        <div style="clear:both;"></div>
    </div>
</div>

<!--底部 版权-->
<div class="footer w1200">
    <p>
        <a href="#">关于我们</a><span>|</span>
        <a href="#">友情链接</a><span>|</span>
        <a href="#">宅客微购社区</a><span>|</span>
        <a href="#">诚征英才</a><span>|</span>
        <a href="#">商家登录</a><span>|</span>
        <a href="#">供应商登录</a><span>|</span>
        <a href="#">热门搜索</a><span>|</span>
        <a href="#">宅客微购新品</a><span>|</span>
        <a href="#">开放平台</a>
    </p>
    <p>
        沪ICP备13044278号<span>|</span>合字B1.B2-20130004<span>|</span>营业执照<span>|</span>互联网药品信息服务资格证<span>|</span>互联网药品交易服务资格证
    </p>
</div>
</body>
</html>
