const { createApp, ref, computed, onMounted } = Vue;
const {ElMessageBox, ElMessage} = ElementPlus;

const app = createApp({
    setup() {
        const voteItems = ref([]);
        const searchId = ref('');
        const createDialogVisible = ref(false);
        const createData = ref({
            itemName: ''
        });
        const editData = ref({
            itemId: '',
            itemName: ''
        });
        const createForm = ref(null);
        const editForm = ref(null);
        const editDialogVisible = ref(false);
        const currentPage = ref(1);
        const pageSize = ref(10);
        const isCreateDataValid = ref(false);
        const isEditDataValid = ref(false);

        const loadVoteItems = async () => {
            try {
                const response = await axios.get('/api/vote/items');
                voteItems.value = response.data;
            } catch (error) {
                ElMessage.error('無法取得投票項目資料');
            }
        };

        const searchVoteItems = async () => {
            if (searchId.value === '') { // 如果搜尋欄為空 則重新讀取資料
                loadVoteItems();
                return;
            }
            try {
                const response = await axios.get(`/api/vote/items/${searchId.value}`);
                if (response.data) {
                    voteItems.value = [response.data];
                } else {
                    voteItems.value = [];
                }
            } catch (error) {
                console.log(error);
                ElMessage.error('無法取得投票項目資料');
            }
        };

        const openCreateDialog = () => {
            createData.value = {
                itemName: ''
            };
            createDialogVisible.value = true;
        }

        const editVoteItem = (itemId, itemName) => {
            editData.value = {itemId, itemName};
            editDialogVisible.value = true;
        }

        const createVoteItem = async () => {
            if (createData.value.itemName === '') {
                ElMessage.warning('請輸入項目名稱');
                return;
            }
            try {
                await axios.post('/api/vote/items/add', null, {
                    params: {
                        itemName: `${createData.value.itemName}`
                    }
                });
                ElMessage.success('成功新增投票項目');
                createDialogVisible.value = false;
                await loadVoteItems();
            } catch (error) {
                ElMessage.error('新增失敗');
            }
        };

        const updateVoteItem = async () => {
            if (editData.value.itemName === '') {
                ElMessage.warning('請輸入項目名稱');
                return;
            }
            try {
                const response = await axios.put(`/api/vote/items/${editData.value.itemId}`, null,
                    {
                        params: {
                            itemName: `${editData.value.itemName}`
                        }
                    }
                );
                if (response.data > 0) {
                    ElMessage.success('成功更新投票項目');
                    editDialogVisible.value = false;
                    await loadVoteItems();
                } else {
                    ElMessage.error('更新失敗');
                }
            } catch (error) {
                ElMessage.error('無法取得投票項目資料');
            }
        };

        const confirmDeleteVoteItem = async (itemId) => {
            ElMessageBox.confirm('此操作將永久刪除該投票項目資料, 是否繼續?', '警告', {
                confirmButtonText: '確定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                await deleteVoteItem(itemId);
            }).catch(() => {
                ElMessage.info('已取消刪除');
            });
        };

        const deleteVoteItem = async (itemId) => {
            try {
                const response = await axios.delete(`/api/vote/items/${itemId}`);
                if (response.data.deleteCount > 0) {
                    let message = '成功刪除投票項目';
                    let deleteLogCount = response.data.deleteLogCount;
                    if (deleteLogCount > 0) {
                        message += `，並刪除了 ${deleteLogCount} 筆投票紀錄`;
                    }
                    ElMessage.success(message);
                    await loadVoteItems();
                } else {
                    ElMessage.error('刪除失敗');
                }
            } catch (error) {
                ElMessage.error('無法刪除投票項目');
            }
        };


        const paginatedVoteItems = computed(() => {
            const start = (currentPage.value - 1) * pageSize.value;
            return voteItems.value.slice(start, start + pageSize.value);
        });

        const handleSizeChange = (newSize) => {
            pageSize.value = newSize;
            currentPage.value = 1;
        };

        const handleCurrentChange = (newPage) => {
            currentPage.value = newPage;
        };

        const formRules = ref({
            itemName: [
                {
                    required: true, message: '請輸入項目名稱', trigger: 'blur'
                },
                {
                    min: 1, message: '項目名稱不能少於 1 個字', trigger: 'blur'
                },
                {
                    max: 64, message: '項目名稱不能超過 64 個字', trigger: 'blur'
                },
                {
                    validator: (rule, value, callback) => {
                        const regex = /^[\u4e00-\u9fa5a-zA-Z0-9. ]+$/;
                        if (regex.test(value)) {
                            callback();
                        } else {
                            callback(new Error('項目名稱只能包含中文、英文字母或數字'));
                        }
                    },
                    trigger: 'blur',
                }
            ],
        });

        const checkCreateDataValid = async () => {
            if (createForm.value) {
                await createForm.value.validate().then((valid) => {
                    isCreateDataValid.value = valid;
                }).catch(() => {
                    isCreateDataValid.value = false;
                });
            }
        }

        const checkEditDataValid = async () => {
            if (editForm.value) {
                await editForm.value.validate().then((valid) => {
                    isEditDataValid.value = valid;
                }).catch(() => {
                    isEditDataValid.value = false;
                });
            }
        };

        onMounted(() => {
            isCreateDataValid.value = false;
            isEditDataValid.value = false;
            loadVoteItems();
        });

        return {
            voteItems,
            searchId,
            openCreateDialog,
            createForm,
            createData,
            createDialogVisible,
            createVoteItem,
            createRules: formRules,
            editForm,
            editData,
            editDialogVisible,
            currentPage,
            pageSize,
            loadVoteItems,
            searchVoteItems,
            editVoteItem,
            updateVoteItem,
            confirmDeleteVoteItem,
            deleteVoteItem,
            paginatedVoteItems,
            handleSizeChange,
            handleCurrentChange,
            isCreateDataValid,
            isEditDataValid,
            checkCreateDataValid,
            checkEditDataValid,
        }
    }
});

app.use(ElementPlus); // 設定語言
app.mount('#app');
