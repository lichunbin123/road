<template>
</template>
<script>
  import { logout} from '@/api/api'
  export default {
    created: function () {
      logout();
      this.$message("退出登录成功")
      this.$router.push("/login")
    }
  }
</script>
