<?php declare(strict_types=1);
use PHPUnit\Framework\TestCase;

final class HashTest extends TestCase
{
    public function test_password_verify(): void
    {
        $this->assertEquals(
            password_verify('mike123', password_hash('mike123', PASSWORD_DEFAULT)),
            true
        );
    }

    public function test_empty(): array
    {
        $stack = [];
        $this->assertEmpty($stack);

        return $stack;
    }

    /**
     * @depends test_empty
     */
    public function test_get_last_key(): void
    {
        array_push($stack, 'bar');
        $this->assertSame(1, array_key_last($stack));
    }

    /**
     * @depends test_empty
     */
    public function test_push(array $stack): array
    {
        array_push($stack, 'foo');
        $this->assertSame('foo', $stack[count($stack)-1]);
        $this->assertNotEmpty($stack);

        return $stack;
    }
}
