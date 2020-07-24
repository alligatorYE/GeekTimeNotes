# git

[toc]

## 暂存区和工作区

### 提交更新

```bash
git add -u
```

### 重命名文件

```bash
git mv readme README.md
```

### 查看版本演变历史

```bash
git log

git log --oneline		#集中显示记录

git log -n4			#查看最近四条记录

git log -n4 --oneline	#集中显示最近四条记录

git log --all --graph	#显示所有分支树

```

### 浏览器查看gitb帮助

```bash
git help --web log
```
### 本地分支关联远程分支
```bash
git branch --set-upstream-to=origin/dev
```