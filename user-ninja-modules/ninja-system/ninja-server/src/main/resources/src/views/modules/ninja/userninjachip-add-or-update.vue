<template>
  <el-dialog
    :title="!dataForm.ninjaId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户id" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="碎片数量" prop="chip">
      <el-input v-model="dataForm.chip" placeholder="碎片数量"></el-input>
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
          ninjaId: 0,
          userId: '',
          chip: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          chip: [
            { required: true, message: '碎片数量不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.ninjaId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.ninjaId) {
            this.$http({
              url: this.$http.adornUrl(`/ninja/userninjachip/info/${this.dataForm.ninjaId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.userNinjaChip.userId
                this.dataForm.chip = data.userNinjaChip.chip
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
              url: this.$http.adornUrl(`/ninja/userninjachip/${!this.dataForm.ninjaId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'ninjaId': this.dataForm.ninjaId || undefined,
                'userId': this.dataForm.userId,
                'chip': this.dataForm.chip
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
