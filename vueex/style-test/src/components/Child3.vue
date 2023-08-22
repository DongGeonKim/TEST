<template>
    <!-- <div :class="$style.child">
        <h2>Child3</h2>
    </div> -->
		<div class="child">
        <h2>Child3</h2>
    </div>
</template>

<script>
export default {
    name : "Child3",
    created() {
        //console.log(this.$style)
    }
}
</script>

<style>
.child { background-color: orange; border:solid 1px black; margin:1.5em; padding: 1.0em;  }
</style>
