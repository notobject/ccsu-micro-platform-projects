Page({
    data: {
      personInfoList: [
        {
          personTitle: '院系',
          personValue: '数计系'
        },
        {
          personTitle: '年级',
          personValue: '大二'
        },
        {
          personTitle: '综测',
          personValue: '25分'
        },
        {
          personTitle: '素拓',
          personValue: '5分'
        }
      ],
      funcList: [
        {
          iconfont: 'iconActivity',
          myActive: '我参与的活动',
          navigateTo: './userActivity/index'
        },
        {
          iconfont: 'iconMessger',
          myActive: '我的消息',
          navigateTo: '#'
        },
      ]
    },
    onLoad(options) {
      
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {
  
    },
    onShow() {
  
    },
    onHide() {
  
    },
    onUnload() {
  
    },
    navigateToMy (){
      wx.navigateTo({
        url: './userInfo/index'
      })
    },
    navigateToF (e) {
      var index = e.currentTarget.dataset.index;
      wx.navigateTo({
        url: this.data.funcList[index].navigateTo
      })
    },
    // tabbar传值(适配iponeX底部tabbar)
    getIsIphoneX(e) {
      this.setData({
        isIphoneX: e.detail.isIphoneX
      })
    },
    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {
  
    },
    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {
  
    },
    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {
  
    },
    /**
     * 页面滚动触发事件的处理函数
     */
    onPageScroll() {
  
    },
    /**
     * 页面尺寸改变时触发
     */
    onResize() {
  
    },
    /**
     * 当前是 tab 页时，点击 tab 时触发
     */
    onTabItemTap() {
  
    }
  })