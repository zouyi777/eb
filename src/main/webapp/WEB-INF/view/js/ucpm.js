/**
 * 通用云平台---移动平台
 */
(function(){
    window.ucpm= window.ucpm || {};
})();
(function(_){
    //访问服务器基本url地址
    _.baseUrl="http://localhost:8080/RESTfulWS_SpringMVC/";

    //列表模板引擎
    var tplCache = {};
    _.tpl = function (str, data) {
        var fn = !/\s/.test(str) ?
            tplCache[str] = tplCache[str] || _.tpl(str) :
            new Function("obj", "var p='';p+='"
                + str.replace(/[\r\n\t]/g, " ")
                    .split('\\').join("\\\\")
                    .split("<%").join("\t")
                    .replace(/((^|%>)[^\t]*)'/g, "$1\r")
                    .replace(/\t=(.*?)%>/g, "'+$1+'")
                    .split("\t").join("';")
                    .split("%>").join("p+='")
                    .split("\r").join("\\'")
                + "';return p;");
        return data ? fn.call(data) : fn;
    };
    //下拉刷新，上拉加载
    _.pullRefreshLoad = function (scrollContainerId,configOptions){
        if(!configOptions){
            return;
        };
        //选项配置
        var ConfigOptions = {
            isNeedRehresh:false,
            isNeedLoad:false,
            pullDownRefreshCallback:null,
            pullUpLoadCallback:null
        }
        //初始化选项配置
        ConfigOptions.isNeedRehresh = configOptions.isNeedRehresh;
        ConfigOptions.isNeedLoad = configOptions.isNeedLoad;
        ConfigOptions.pullDownRefreshCallback = configOptions.pullDownRefreshCallback;
        ConfigOptions.pullUpLoadCallback = configOptions.pullUpLoadCallback;

        var startPageY = 0;//触摸开始时，相对于文档顶部的Y坐标
        var scrollContainer = document.getElementById(scrollContainerId);//获取滚动容器
        /**
         * 创建刷新视图最外层的div标签，并且设置样式属性
         */
        var isRefresh = false;
        var refreshViewDiv = document.createElement("div");
        refreshViewDiv.style.textAlign="center";
        refreshViewDiv.style.lineHeight="60px";
        refreshViewDiv.style.marginTop="-60px";
        //创建刷新视图中箭头图标
        var arrowViewImg = document.createElement("img");
        arrowViewImg.style.width = "20px";
        arrowViewImg.style.height = "20px";
        arrowViewImg.style.verticalAlign = "middle";
        arrowViewImg.style.marginRight = "5px";
        arrowViewImg.src = "../images/icon/xiafan.png"
        refreshViewDiv.appendChild(arrowViewImg);
        //创建刷新视图中的正在刷新图标
        var refreshingViewImg = document.createElement("img");
        refreshingViewImg.style.width = "25px";
        refreshingViewImg.style.height = "25px";
        refreshingViewImg.style.verticalAlign = "middle";
        refreshingViewImg.style.marginRight = "5px";
        refreshingViewImg.style.display = "none";
        refreshingViewImg.src = "../images/icon/loading.gif";
        refreshViewDiv.appendChild(refreshingViewImg);
        //创建刷新视图中的下拉刷新提示语
        var refreshViewSpan = document.createElement("span");
        refreshViewSpan.innerHTML = "下拉刷新";
        refreshViewDiv.appendChild(refreshViewSpan);
        //将刷新视图插入到滚动容器第一个子元素前面
        if(scrollContainer.children[0]){
            scrollContainer.insertBefore(refreshViewDiv,scrollContainer.children[0]);
        }else{
            scrollContainer.appendChild(refreshViewDiv);
        }
        //创建刷新完成后对象
        var refreshFinishedOptions = {
            refreshFinished:true,
            success:function(){
                var time = setInterval(function () {
                    refreshViewDiv.style.marginTop = parseFloat(refreshViewDiv.style.marginTop) - 3 + 'px';
                    if (parseFloat(refreshViewDiv.style.marginTop) <= -59) {
                        clearInterval(time);
                        refreshViewDiv.style.marginTop = "-60px";
                        refreshViewSpan.innerHTML = "下拉刷新";
                        refreshingViewImg.style.display = "none";
                        arrowViewImg.style.transform = "none";//恢复旋转
                        arrowViewImg.style.display = "";
                    }
                });
            },
            error:function(){

            }
        };
        /**
         * 创建加载视图最外层的div标签，并且设置样式属性
         */
        var containerScrollTop = 0;
        var isLoad = false;
        var loadViewDiv = document.createElement("div");
        loadViewDiv.style.textAlign="center";
        loadViewDiv.style.lineHeight="60px";
        loadViewDiv.style.clear="both";
        //创建加载视图中的正在加载动态图标，设置样式属性，并且添加到div中
        var loadViewImg = document.createElement("img");
        loadViewImg.style.width ="25px";
        loadViewImg.style.height = "25px";
        loadViewImg.style.verticalAlign="middle";
        loadViewImg.style.display = "none";
        loadViewImg.src ="../images/icon/loading.gif";
        loadViewDiv.appendChild(loadViewImg);
        //创建加载视图中的“上拉加载更多”提示
        var loadViewSpan = document.createElement("span");
        loadViewSpan.innerHTML="上拉加载更多";
        loadViewDiv.appendChild(loadViewSpan);
        //将加载视图追加在滚动容器的最后面
        scrollContainer.appendChild(loadViewDiv);
        //创建加载完成后对象
        var loadFinishOptions = {
            isFinished:true,
            success:function(){
                scrollContainer.scrollTo(0,containerScrollTop);
                loadViewImg.style.display="none";
                loadViewSpan.innerHTML="上拉加载更多";
            },
            error:function(){

            }
        };
        /**
         * 添加触摸事件监听
         */
        scrollContainer.addEventListener("touchstart",function(event){
            startPageY = event.touches[0].pageY;//记录触摸开始时，相对于文档顶部的Y坐标
        });
        scrollContainer.addEventListener("touchmove",function(event){
            var currentageY = event.touches[0].pageY;
            var touchDis = currentageY - startPageY ;
            if(scrollContainer.scrollTop <= 0 && touchDis > 0){//当滚动容器滚动到顶部,并且触摸向下滑动
                refreshViewDiv.style.marginTop = parseFloat(refreshViewDiv.style.marginTop) + touchDis + "px";//使刷新视图的margin-top随着下拉距离增加而增加
                startPageY = currentageY;
                if(parseFloat(refreshViewDiv.style.marginTop) >= 0){//如果刷新视图已经显示完全了
                    refreshViewSpan.innerHTML = "释放刷新";
                    arrowViewImg.style.transform = "rotate(180deg)";//箭头方向旋转180度
                    isRefresh = true;
                }
            }else if((scrollContainer.scrollTop + scrollContainer.clientHeight >= scrollContainer.scrollHeight) && touchDis < 0 ){//当滚动容器滚动到底部,并且触摸向上滑动
                isLoad = true;
            }
        });
        scrollContainer.addEventListener("touchend",function(event){
            if (isRefresh) {
                refreshingViewImg.style.display = "";
                refreshViewSpan.innerHTML = "正在刷新"
                arrowViewImg.style.display = "none";
                ConfigOptions.pullDownRefreshCallback(refreshFinishedOptions);
                isRefresh = false;
            } else {
                refreshFinishedOptions.success();
            }
            if(isLoad){
                containerScrollTop = scrollContainer.scrollTop;
                loadViewImg.style.display = "";
                loadViewSpan.innerHTML = "加载中";
                //调用上拉加载回调函数请求数据
                ConfigOptions.pullUpLoadCallback(loadFinishOptions);
                isLoad = false ;
            }
        });
    };
})(ucpm);