Page({
  data: {
    // 图片
    imgUrls: [
      '../../static/images/1.png',
      '../../static/images/2.png',
      '../../static/images/3.png',
      '../../static/images/4.png'
    ],
    CampusEventMenuList: [
      '最新',
      '最热',
      '活动',
      '通知',
      '招聘',
      '公示'
    ],
    bannarIndex: 0,//轮播图下标
    CampusEventMenuIndex: 0, //校园动态菜单栏下标,
    isIphoneX: false
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
  // 轮播图实时下标
  swiperChange(e) {
    this.setData({
      bannarIndex: e.detail.current
    })
  },
  CampusEventMenuClick(e) {
    this.setData({
      CampusEventMenuIndex: e.currentTarget.dataset.index
    })
  },
  // tabbar传值(适配iponeX底部tabbar)
  getIsIphoneX(e) {
    this.setData({
      isIphoneX: e.detail.isIphoneX
    })
  },
  // 文章跳转
  navigaTo() {
    wx.navigateTo({
      url: './articleDetial/index'
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