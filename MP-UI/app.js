App({
    onLaunch() {
        // 小程序加载完成时触发一次
        wx.hideTabBar(); //隐藏原生tabbar
        // this.getUserInfo();
    },
    onShow() {
        wx.hideTabBar(); //隐藏原生tabbar
        // 小程序从后台进入前台时触发
    },
    onHide() {
        // 小程序前台从进入后台时触发
    },
    onError(msg) {
        // 小程序错误监听函数
    },
    onPageNotFound() {
        // 进入的页面不存在时触发
    },
    /**
     * 获取用户信息
     */
    getUserInfo: function (cb) {
        var that = this;
        wx.login({
            success: function (res) {
                // 登录成功
                var code = res.code; // 登录凭证
                console.log(code)
                wx.getSetting({
                    success: res => {
                        if (res.authSetting['scope.userInfo']) {
                            wx.getUserInfo({
                                success: res => {
                                    // this.globalData.userInfo = res.userInfo
                                    console.log(res)
                                }
                            });
                        }
                    }
                });
            }
        })
    },
    // baseURL
    globaData: {
        url: ''
    }
});