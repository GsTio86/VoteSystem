<template>
  <div style="display: flex; justify-content: center; align-items: center; height: auto">
    <el-row type="flex" justify="center" align="middle" style="width: 100%;">
      <el-col :span="24" style="display: flex; justify-content: center; margin-bottom: 20px;">
        <VoteStats />
      </el-col>
      <el-col :span="24" style="display: flex; justify-content: center; margin-bottom: 20px;">
        <VoteForm />
      </el-col>
    </el-row>
  </div>
</template>
<script>
import VoteStats from "@/components/VoteStats.vue";
import VoteForm from "@/components/VoteForm.vue";

export default {
  name: 'App',
  components: {
    VoteStats,
    VoteForm
  },
}
</script>
<style scoped>

</style>
