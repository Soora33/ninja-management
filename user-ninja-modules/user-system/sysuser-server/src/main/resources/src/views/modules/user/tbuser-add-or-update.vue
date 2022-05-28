<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="name">
      <el-input v-model="dataForm.name" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="vip等级" prop="vipLevel">
      <el-input v-model="dataForm.vipLevel" placeholder="vip等级"></el-input>
    </el-form-item>
    <el-form-item label="金币数量" prop="gold">
      <el-input v-model="dataForm.gold" placeholder="金币数量"></el-input>
    </el-form-item>
    <el-form-item label="今日可购买次数" prop="buyCount">
      <el-input v-model="dataForm.buyCount" placeholder="今日可购买次数"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="tel">
      <el-input v-model="dataForm.tel" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item label="累计充值余额" prop="priceTotal">
      <el-input v-model="dataForm.priceTotal" placeholder="累计充值余额"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          name: '',
          password: '',
          vipLevel: '',
          gold: '',
          buyCount: '',
          tel: '',
          priceTotal: ''
        },
        dataRule: {
          name: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          vipLevel: [
            { required: true, message: 'vip等级不能为空', trigger: 'blur' }
          ],
          gold: [
            { required: true, message: '金币数量不能为空', trigger: 'blur' }
          ],
          buyCount: [
            { required: true, message: '今日可购买次数不能为空', trigger: 'blur' }
          ],
          tel: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          priceTotal: [
            { required: true, message: '累计充值余额不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/user/tbuser/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.tbUser.name
                this.dataForm.password = data.tbUser.password
                this.dataForm.vipLevel = data.tbUser.vipLevel
                this.dataForm.gold = data.tbUser.gold
                this.dataForm.buyCount = data.tbUser.buyCount
                this.dataForm.tel = data.tbUser.tel
                this.dataForm.priceTotal = data.tbUser.priceTotal
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/user/tbuser/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'password': this.dataForm.password,
                'vipLevel': this.dataForm.vipLevel,
                'gold': this.dataForm.gold,
                'buyCount': this.dataForm.buyCount,
                'tel': this.dataForm.tel,
                'priceTotal': this.dataForm.priceTotal
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
