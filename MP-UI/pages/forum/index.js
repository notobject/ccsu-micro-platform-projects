Page({
    data: {
      hotArticleTitleList: [
        {
          hotArticleTitle: '非常真诚的，希望在大学',
        },
        {
          hotArticleTitle: '毕业结婚问题',
        },
        {
          hotArticleTitle: '求推荐小说',
        }
      ],
      articleList: [
        {
          authorName: '曹孝双',
          authorTime: '2018-11-28',
          authorTip: '校友',
          articleName: '拼多多补招之被拒经验',
          articleContent: '留学生，今年7月毕业，九月中回国，本来想gap半年，发现应届生标准好像不一样， 就想着先找工超出内容部分隐藏'
        },
        {
          authorName: '曹孝双',
          authorTime: '2018-11-28',
          authorTip: '校友',
          articleName: '拼多多补招之被拒经验',
          articleContent: '留学生，今年7月毕业，九月中回国，本来想gap半年，发现应届生标准好像不一样'
        }
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
    navigateToArticle() {
      wx.navigateTo({
        url: './articleDetail/index'
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