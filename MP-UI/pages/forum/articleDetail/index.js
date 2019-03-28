/**
 * 帖子详情
 * 作者 cxs
 */
Page({
  data: {
    artcleList:{
      artcleTitle: '30w996和20w965怎么选？',
      authorList:{
        authorName: '李四',
        publicationTime: '2018-11-28 15:23:59'
      }
    },
    commentList: [
      {
        authorList:{
          authorName: '李四',
          publicationTime: '2018-11-28 15:23:59'
        },
        commentNum: 1,
        praiseNum: 1,
        articleContent: '别去小公司，坑的一笔',
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
  navigateToComment (){
    wx.navigateTo({
      url: './commentDetail/index'
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